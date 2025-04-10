package colorful.starbucks.auth.dto.request;

import colorful.starbucks.auth.vo.request.EmailVerifyCodeRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EmailVerifyCodeRequestDto {

    private String email;
    private String code;

    @Builder
    private EmailVerifyCodeRequestDto(String email, String code) {
        this.email = email;
        this.code = code;
    }

    public static EmailVerifyCodeRequestDto from(EmailVerifyCodeRequestVo emailVerifyCodeRequestVo) {
        return EmailVerifyCodeRequestDto.builder()
                .email(emailVerifyCodeRequestVo.getEmail())
                .code(emailVerifyCodeRequestVo.getCode())
                .build();
    }
}

