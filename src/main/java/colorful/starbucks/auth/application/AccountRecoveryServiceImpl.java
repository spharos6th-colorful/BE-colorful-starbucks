package colorful.starbucks.auth.application;

import colorful.starbucks.auth.dto.request.MemberEmailFindRequestDto;
import colorful.starbucks.auth.dto.request.MemberPasswordResetRequestDto;
import colorful.starbucks.auth.dto.response.MemberEmailFindResponseDto;
import colorful.starbucks.auth.dto.response.MemberPasswordResetResponseDto;
import colorful.starbucks.common.exception.BaseException;
import colorful.starbucks.common.response.ResponseStatus;
import colorful.starbucks.common.service.EmailSendService;
import colorful.starbucks.member.domain.Member;
import colorful.starbucks.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountRecoveryServiceImpl implements AccountRecoveryService {

    private final EmailSendService emailSendService;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    @Override
    public MemberPasswordResetResponseDto findPassword(MemberPasswordResetRequestDto memberPasswordResetRequestDto) {

        Member member = memberRepository.findByEmailAndMemberNameAndPhoneNumber(memberPasswordResetRequestDto.getEmail(),
                        memberPasswordResetRequestDto.getMemberName(), memberPasswordResetRequestDto.getPhoneNumber())
                .orElseThrow(() -> new BaseException(ResponseStatus.NO_EXIST_USER));
        memberPasswordResetRequestDto.generateTempPassword(passwordEncoder);
        member.updatePassword(memberPasswordResetRequestDto.getEncodedPassword());
        emailSendService.sendTempPassword(member.getEmail(), memberPasswordResetRequestDto.getTempPassword());

        return MemberPasswordResetResponseDto.from("임시 비밀번호가 이메일로 전송 되었습니다.");
    }

    @Transactional
    @Override
    public MemberEmailFindResponseDto findEmail(MemberEmailFindRequestDto memberEmailFindRequestDto) {
        Member member = memberRepository.findByMemberNameAndPhoneNumber(memberEmailFindRequestDto.getMemberName(),
                         memberEmailFindRequestDto.getPhoneNumber())
                .orElseThrow(() -> new BaseException(ResponseStatus.NO_EXIST_USER));
        return MemberEmailFindResponseDto.from(member.getEmail());
    }

    @Override
    public boolean isEmailDuplicated(String email) {
        return memberRepository.existsByEmail(email);
    }
}
