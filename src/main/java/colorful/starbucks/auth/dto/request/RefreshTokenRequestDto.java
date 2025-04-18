package colorful.starbucks.auth.dto.request;

import colorful.starbucks.auth.vo.request.RefreshTokenRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RefreshTokenRequestDto {

    private String refreshToken;


    @Builder
    private RefreshTokenRequestDto(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public static RefreshTokenRequestDto from(RefreshTokenRequestVo refreshTokenRequestVo) {
        return RefreshTokenRequestDto.builder()
                .refreshToken(refreshTokenRequestVo.getRefreshToken())
                .build() ;
    }

}
