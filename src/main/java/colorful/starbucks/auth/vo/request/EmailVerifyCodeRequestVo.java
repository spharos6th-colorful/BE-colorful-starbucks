package colorful.starbucks.auth.vo.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class EmailVerifyCodeRequestVo {

    private String email;
    private String code;

    @Builder
    private EmailVerifyCodeRequestVo(String email, String code) {
        this.email = email;
        this.code = code;
    }
}


