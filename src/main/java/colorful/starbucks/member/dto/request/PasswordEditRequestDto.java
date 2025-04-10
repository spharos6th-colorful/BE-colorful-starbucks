package colorful.starbucks.member.dto.request;

import colorful.starbucks.member.vo.request.PasswordEditRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PasswordEditRequestDto {

    private String newPassword;
    private String memberUuid;

    @Builder
    private PasswordEditRequestDto(String newPassword,
                                   String memberUuid) {
        this.newPassword = newPassword;
        this.memberUuid = memberUuid;
    }

    public static PasswordEditRequestDto of(PasswordEditRequestVo passwordEditRequestVo, String memberUuid) {
        return PasswordEditRequestDto.builder()
                .newPassword(passwordEditRequestVo.getNewPassword())
                .memberUuid(memberUuid)
                .build();
    }


}
