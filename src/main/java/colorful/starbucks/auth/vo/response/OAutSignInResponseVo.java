package colorful.starbucks.auth.vo.response;

import colorful.starbucks.auth.domain.SignType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OAutSignInResponseVo {
    private String accessToken;
    private String refreshToken;
    private SignType signType;

    @Builder
    private OAutSignInResponseVo(String accessToken,
                                 String refreshToken,
                                 SignType signType) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.signType = signType;
    }
}
