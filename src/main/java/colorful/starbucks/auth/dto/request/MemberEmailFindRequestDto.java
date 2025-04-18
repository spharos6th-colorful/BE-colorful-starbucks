package colorful.starbucks.auth.dto.request;

import colorful.starbucks.auth.vo.request.MemberEmailFindRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
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
