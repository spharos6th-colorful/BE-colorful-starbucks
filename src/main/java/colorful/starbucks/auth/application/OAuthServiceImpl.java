package colorful.starbucks.auth.application;

import colorful.starbucks.auth.domain.CustomUserDetails;
import colorful.starbucks.auth.domain.OAuth;
import colorful.starbucks.auth.domain.SignType;
import colorful.starbucks.auth.dto.request.OAuthSignInRequestDto;
import colorful.starbucks.auth.dto.response.KakaoUserInfo;
import colorful.starbucks.auth.dto.response.OAutSignInResponseDto;
import colorful.starbucks.auth.infrastructure.AuthRepository;
import colorful.starbucks.auth.infrastructure.OAuthRepository;
import colorful.starbucks.common.exception.BaseException;
import colorful.starbucks.common.response.ResponseStatus;
import colorful.starbucks.common.util.TokenGenerator;
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
    private final TokenGenerator tokenGenerator;
    private final OAuthRepository oAuthRepository;
    private final AuthRepository authRepository;


    @Transactional
    @Override
    public OAutSignInResponseDto kakaoSignIn(OAuthSignInRequestDto OAuthSignInRequestDto) {
        KakaoUserInfo userInfo = OAuthSignInRequestDto.fetchUserInfo(kakaoApiService);

        if (userInfo.getEmail() == null || userInfo.getEmail().isBlank()) {
            throw new BaseException(ResponseStatus.INVALID_EMAIL_ADDRESS);
        }

        OAuth oAuth = findOrCreateKakaoOAuth(userInfo);

        UserDetails userDetails = new CustomUserDetails(oAuth);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities()
        );

        String accessToken = tokenGenerator.issueTokens(authentication).getAccessToken();
        String refreshToken = tokenGenerator.issueTokens(authentication).getRefreshToken();

        return OAutSignInResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .signType(oAuth.getSignType().name())
                .build();
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
