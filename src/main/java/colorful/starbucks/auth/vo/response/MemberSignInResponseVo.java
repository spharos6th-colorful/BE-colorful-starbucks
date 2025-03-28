package colorful.starbucks.auth.vo.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberSignInResponseVo {

    private String accessToken;

    @Builder
    public MemberSignInResponseVo(String accessToken) {
        this.accessToken = accessToken;
    }
}
