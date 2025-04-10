package colorful.starbucks.auth.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OAutSignInResponseVo {
    private String accessToken;
    private String refreshToken;
    private String signType;

    @Builder
    private OAutSignInResponseVo(String accessToken,
                                 String refreshToken,
                                 String signType) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.signType = signType;
    }
}
