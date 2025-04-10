package colorful.starbucks.auth.dto.response;

import colorful.starbucks.auth.vo.response.OAutSignInResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OAuthSignInResponseDto {
    private String accessToken;
    private String refreshToken;
    private String signType;

    @Builder
    private OAuthSignInResponseDto(String accessToken,
                                   String refreshToken,
                                   String signType) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.signType = signType;
    }
    public static OAuthSignInResponseDto of(String accessToken, String refreshToken, String signType) {
        return OAuthSignInResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .signType(signType)
                .build();
    }
    public OAutSignInResponseVo toVo() {
        return OAutSignInResponseVo.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .signType(signType)
                .build();
    }
}
