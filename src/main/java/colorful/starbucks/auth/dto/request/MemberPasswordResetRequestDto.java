package colorful.starbucks.auth.dto.request;

import colorful.starbucks.auth.vo.request.MemberPasswordResetRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberPasswordResetRequestDto {

    private String memberName;

    private String email;

    private String memberBirth;

    @Builder
    private MemberPasswordResetRequestDto(String memberName,
                                         String email,
                                         String memberBirth) {
        this.memberName = memberName;
        this.email = email;
        this.memberBirth = memberBirth;
    }

    public static MemberPasswordResetRequestDto from(MemberPasswordResetRequestVo memberPasswordResetRequestVo) {
        return MemberPasswordResetRequestDto.builder()
                .memberName(memberPasswordResetRequestVo.getMemberName())
                .email(memberPasswordResetRequestVo.getEmail())
                .memberBirth(memberPasswordResetRequestVo.getMemberBirth())
                .build();
    }
}
