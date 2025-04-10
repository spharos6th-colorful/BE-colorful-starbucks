package colorful.starbucks.auth.dto.request;

import colorful.starbucks.auth.domain.CustomUserDetails;
import colorful.starbucks.auth.vo.request.RefreshTokenRequestVo;
import colorful.starbucks.common.jwt.JwtTokenProvider;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

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
