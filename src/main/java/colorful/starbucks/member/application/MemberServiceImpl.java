package colorful.starbucks.member.application;

import colorful.starbucks.member.domain.Member;
import colorful.starbucks.member.dto.request.MemberMyPageEditRequestDto;
import colorful.starbucks.member.dto.request.PasswordEditRequestDto;
import colorful.starbucks.member.dto.response.MemberMyPageResponseDto;
import colorful.starbucks.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
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

        Member member = memberRepository.findByMemberUuid(memberMyPageEditRequestDto.getMemberUuid());
        Member updatedMyPage = buildUpdatedMyPage(member, memberMyPageEditRequestDto);

        memberRepository.save(updatedMyPage);
    }

    @Transactional
    @Override
    public void editPassword(PasswordEditRequestDto passwordEditRequestDto) {

        Member member = memberRepository.findByMemberUuid(passwordEditRequestDto.getMemberUuid());
        Member updatedPassword = buildUpdatedPassword(member, passwordEncoder.encode(passwordEditRequestDto.getNewPassword()));

        memberRepository.save(updatedPassword);
    }

    @Override
    public MemberMyPageResponseDto getMyPage(String memberUuid) {

        Member member = memberRepository.findByMemberUuid(memberUuid);

        return MemberMyPageResponseDto.from(member);
    }


    private Member buildUpdatedPassword(Member member, String password){
        return Member.builder()
                .id(member.getId())
                .memberUuid(member.getMemberUuid())
                .memberName(member.getMemberName())
                .email(member.getEmail())
                .password(password)
                .phoneNumber(member.getPhoneNumber())
                .nickName(member.getNickName())
                .memberLevel(member.getMemberLevel())
                .memberBirth(member.getMemberBirth())
                .gender(member.getGender())
                .build();
    }

    private Member buildUpdatedMyPage(Member member, MemberMyPageEditRequestDto memberMyPageEditRequestDto) {
        return Member.builder()
                .id(member.getId())
                .email(member.getEmail())
                .memberUuid(member.getMemberUuid())
                .memberName(member.getMemberName())
                .password(member.getPassword())
                .memberLevel(member.getMemberLevel())
                .memberBirth(member.getMemberBirth())
                .gender(member.getGender())
                .phoneNumber(memberMyPageEditRequestDto.getPhoneNumber())
                .nickName(memberMyPageEditRequestDto.getNickName())
                .build();
    }

}
