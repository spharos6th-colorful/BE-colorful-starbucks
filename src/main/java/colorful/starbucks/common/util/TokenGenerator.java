package colorful.starbucks.common.util;

import colorful.starbucks.auth.application.RefreshTokenRedisService;
import colorful.starbucks.auth.domain.CustomUserDetails;
import colorful.starbucks.auth.dto.response.MemberSignInResponseDto;
import colorful.starbucks.common.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenGenerator {
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRedisService refreshTokenRedisService;


    public MemberSignInResponseDto issueTokens(Authentication authentication) {
        String accessToken = createAccessToken(authentication);
        String refreshToken = createRefreshToken(authentication);

        String uuid = ((CustomUserDetails) authentication.getPrincipal()).getMemberUuid();
        refreshTokenRedisService.saveRefreshToken(
                uuid,
                refreshToken,
                jwtTokenProvider.getRefreshTokenExpireTime()
        );

        return MemberSignInResponseDto.from(accessToken, refreshToken);
    }

    private String createAccessToken(Authentication authentication) {
        return jwtTokenProvider.generateAccessToken(authentication);
    }

    private String createRefreshToken(Authentication authentication) {
        return jwtTokenProvider.generateRefreshToken(authentication);
    }

}
