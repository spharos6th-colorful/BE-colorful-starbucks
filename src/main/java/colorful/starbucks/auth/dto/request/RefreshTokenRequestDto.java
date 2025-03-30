package colorful.starbucks.auth.dto.request;

import colorful.starbucks.auth.vo.request.RefreshTokenRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RefreshTokenRequestDto {

    private String refreshToken;

    public static RefreshTokenRequestDto from(RefreshTokenRequestVo refreshTokenRequestVo) {
        return RefreshTokenRequestDto.builder()
                .refreshToken(refreshTokenRequestVo.getRefreshToken())
                .build() ;
    }

}
