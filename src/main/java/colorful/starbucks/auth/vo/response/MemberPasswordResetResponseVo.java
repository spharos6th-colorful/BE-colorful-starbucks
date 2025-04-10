package colorful.starbucks.auth.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberPasswordResetResponseVo {

    private String message;

    @Builder
    private MemberPasswordResetResponseVo(String message) {
        this.message = message;
    }

}
