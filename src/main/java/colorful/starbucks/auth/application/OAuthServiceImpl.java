package colorful.starbucks.auth.application;

import colorful.starbucks.auth.domain.CustomUserDetails;
import colorful.starbucks.auth.domain.OAuth;
import colorful.starbucks.auth.domain.SignType;
import colorful.starbucks.auth.dto.request.OAuthSignInRequestDto;
import colorful.starbucks.auth.dto.response.KakaoUserInfo;
import colorful.starbucks.auth.dto.response.OAuthSignInResponseDto;
import colorful.starbucks.auth.infrastructure.AuthRepository;
import colorful.starbucks.auth.infrastructure.OAuthRepository;
import colorful.starbucks.common.exception.BaseException;
import colorful.starbucks.common.jwt.JwtTokenProvider;
import colorful.starbucks.common.response.ResponseStatus;
import colorful.starbucks.common.util.UuidGenerator;
import colorful.starbucks.member.domain.Member;
import colorful.starbucks.member.domain.MemberLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OAuthServiceImpl implements OAuthService {

    private final KakaoApiService kakaoApiService;
    private final JwtTokenProvider jwtTokenProvider;
    private final OAuthRepository oAuthRepository;
    private final AuthRepository authRepository;
    private final RefreshTokenRedisService refreshTokenRedisService;


    @Transactional
    @Override
    public OAuthSignInResponseDto kakaoSignIn(OAuthSignInRequestDto OAuthSignInRequestDto) {
        KakaoUserInfo userInfo = kakaoApiService.getUserInfo(kakaoApiService.getAccessToken(OAuthSignInRequestDto.getCode()));

        if (userInfo.getEmail() == null || userInfo.getEmail().isBlank()) {
            throw new BaseException(ResponseStatus.INVALID_EMAIL_ADDRESS);
        }

        OAuth oAuth = findOrCreateKakaoOAuth(userInfo);

        UserDetails userDetails = new CustomUserDetails(oAuth);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities()
        );

        String accessToken = jwtTokenProvider.generateAccessToken(authentication);
        String refreshToken = jwtTokenProvider.generateRefreshToken(authentication);

        refreshTokenRedisService.saveRefreshToken(
                ((CustomUserDetails) authentication.getPrincipal()).getMemberUuid(),
                refreshToken,
                jwtTokenProvider.getRefreshTokenExpireTime()
        );

        return OAuthSignInResponseDto.of(accessToken, refreshToken, SignType.KAKAO);
    }

    private OAuth findOrCreateKakaoOAuth(KakaoUserInfo kakaoUserInfo) {
        return oAuthRepository.findBySignTypeAndProviderId(SignType.KAKAO, kakaoUserInfo.getId())
                .orElseGet(() -> {
                    Member newMember = authRepository.save(
                            Member.builder()
                                    .email(kakaoUserInfo.getEmail())
                                    .memberUuid(UuidGenerator.generateUuid())
                                    .memberLevel(MemberLevel.WHITE)
                                    .build()
                    );

                    return oAuthRepository.save(
                            OAuth.builder()
                                    .signType(SignType.KAKAO)
                                    .providerId(kakaoUserInfo.getId())
                                    .memberUuid(newMember.getMemberUuid())
                                    .build()
                    );
                });
    }
}
