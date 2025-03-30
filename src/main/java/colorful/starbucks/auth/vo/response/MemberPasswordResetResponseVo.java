package colorful.starbucks.auth.vo.response;

import lombok.Builder;

public class MemberPasswordResetResponseVo {

    private String password;

    @Builder
    private MemberPasswordResetResponseVo(String password) {
        this.password = password;
    }

}
