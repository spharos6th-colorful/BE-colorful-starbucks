package colorful.starbucks.auth.dto.request;

import colorful.starbucks.auth.vo.request.MemberEmailFindRequestVo;
import colorful.starbucks.auth.vo.request.MemberSignInRequestVo;
import lombok.Builder;
import lombok.Getter;

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
}
