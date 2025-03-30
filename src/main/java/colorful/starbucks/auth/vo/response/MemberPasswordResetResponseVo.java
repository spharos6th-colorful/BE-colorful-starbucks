package colorful.starbucks.auth.vo.response;

import lombok.Builder;

public class MemberPasswordResetResponseVo {

    private String message;

    @Builder
    private MemberPasswordResetResponseVo(String message) {
        this.message = message;
    }

}
