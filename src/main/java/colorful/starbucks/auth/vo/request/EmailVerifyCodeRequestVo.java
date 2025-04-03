package colorful.starbucks.auth.vo.request;

import lombok.Getter;

@Getter
public class EmailVerifyCodeRequestVo {

    private String email;
    private String code;

}


