package colorful.starbucks.auth.vo.response;

import colorful.starbucks.auth.dto.response.AccessTokenResponseDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AccessTokenResponseVo {

    private String accessToken;

    @Builder
    private AccessTokenResponseVo(String accessToken) {
        this.accessToken = accessToken;
    }

    public static AccessTokenResponseVo from(AccessTokenResponseDto accessTokenResponseDto) {
        return AccessTokenResponseVo.builder()
                .accessToken(accessTokenResponseDto.getAccessToken())
                .build();
    }
}
