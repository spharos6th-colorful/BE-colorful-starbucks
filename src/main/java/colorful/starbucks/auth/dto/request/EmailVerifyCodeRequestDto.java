package colorful.starbucks.auth.dto.request;

import colorful.starbucks.auth.vo.request.EmailVerifyCodeRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class EmailVerifyCodeRequestDto {

    private final String email;
    private final String code;

    @Builder
    private EmailVerifyCodeRequestDto(String email, String code) {
        this.email = email;
        this.code = code;
    }

    public static EmailVerifyCodeRequestDto from(EmailVerifyCodeRequestVo vo) {
        return EmailVerifyCodeRequestDto.builder()
                .email(vo.getEmail())
                .code(vo.getCode())
                .build();
    }
}

