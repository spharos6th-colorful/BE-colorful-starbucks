package colorful.starbucks.member.application;

import colorful.starbucks.common.exception.BaseException;
import colorful.starbucks.common.response.ResponseStatus;
import colorful.starbucks.member.domain.Member;
import colorful.starbucks.member.dto.request.MemberMyPageEditRequestDto;
import colorful.starbucks.member.dto.request.PasswordEditRequestDto;
import colorful.starbucks.member.dto.response.MemberMyPageResponseDto;
import colorful.starbucks.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void editMyPage(MemberMyPageEditRequestDto memberMyPageEditRequestDto) {

        Member member = memberRepository.findAllByMemberUuid(memberMyPageEditRequestDto.getMemberUuid())
                .orElseThrow(() -> new BaseException(ResponseStatus.RESOURCE_NOT_FOUND));

        member.editMypage(memberMyPageEditRequestDto.getPhoneNumber(), memberMyPageEditRequestDto.getNickName());
    }

    @Transactional
    @Override
    public void editPassword(PasswordEditRequestDto passwordEditRequestDto) {
        Member member = memberRepository.findAllByMemberUuid(passwordEditRequestDto.getMemberUuid())
                .orElseThrow(() -> new BaseException(ResponseStatus.RESOURCE_NOT_FOUND));

        member.updatePassword(passwordEncoder.encode(passwordEditRequestDto.getNewPassword()));
    }

    @Override
    public MemberMyPageResponseDto getMyPage(String memberUuid) {

        Member member = memberRepository.findByMemberUuid(memberUuid)
                .orElseThrow(() -> new BaseException(ResponseStatus.RESOURCE_NOT_FOUND));

        return MemberMyPageResponseDto.from(member);
    }


}
