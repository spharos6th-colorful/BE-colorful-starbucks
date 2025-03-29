package colorful.starbucks.auth.vo.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberSignInResponseVo {

    private String accessToken;

    private String refreshToken;

    @Builder
    private MemberSignInResponseVo(String accessToken,
                                  String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
