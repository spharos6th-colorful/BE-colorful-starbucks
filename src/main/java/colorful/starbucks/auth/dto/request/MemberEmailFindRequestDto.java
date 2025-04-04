package colorful.starbucks.auth.dto.request;

import colorful.starbucks.member.domain.Member;
import colorful.starbucks.auth.infrastructure.AuthRepository;
import colorful.starbucks.auth.vo.request.MemberEmailFindRequestVo;
import lombok.Builder;
import lombok.Getter;

import java.util.Optional;

@Getter
public class MemberEmailFindRequestDto {

    private String memberName;

    private String phoneNumber;

    @Builder
    private MemberEmailFindRequestDto(String memberName, String phoneNumber) {
        this.memberName = memberName;
        this.phoneNumber = phoneNumber;
    }

    public static MemberEmailFindRequestDto from(MemberEmailFindRequestVo memberEmailFindRequestVo) {
        return MemberEmailFindRequestDto.builder()
                .memberName(memberEmailFindRequestVo.getMemberName())
                .phoneNumber(memberEmailFindRequestVo.getPhoneNumber())
                .build();
    }

    public Optional<Member>  findByMemberNameAndPhoneNumber(AuthRepository authRepository) {
        return authRepository.findByMemberNameAndPhoneNumber(memberName, phoneNumber);

    }


}
