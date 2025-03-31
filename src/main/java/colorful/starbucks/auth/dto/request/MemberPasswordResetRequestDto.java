package colorful.starbucks.auth.dto.request;

import colorful.starbucks.auth.vo.request.MemberPasswordResetRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberPasswordResetRequestDto {

    private String memberName;

    private String email;

    private String phoneNumber;

    @Builder
    private MemberPasswordResetRequestDto(String memberName,
                                         String email,
                                         String phoneNumber) {
        this.memberName = memberName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public static MemberPasswordResetRequestDto from(MemberPasswordResetRequestVo memberPasswordResetRequestVo) {
        return MemberPasswordResetRequestDto.builder()
                .memberName(memberPasswordResetRequestVo.getMemberName())
                .email(memberPasswordResetRequestVo.getEmail())
                .phoneNumber(memberPasswordResetRequestVo.getPhoneNumber())
                .build();
    }
}
