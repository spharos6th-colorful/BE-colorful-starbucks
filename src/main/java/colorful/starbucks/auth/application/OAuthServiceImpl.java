package colorful.starbucks.auth.application;

import colorful.starbucks.auth.domain.CustomUserDetails;
import colorful.starbucks.auth.domain.SignType;
import colorful.starbucks.auth.dto.request.KakaoSignInRequestDto;
import colorful.starbucks.auth.dto.response.KakaoUserInfo;
import colorful.starbucks.auth.dto.response.MemberSignInResponseDto;
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


    @Transactional
    @Override
    public MemberSignInResponseDto kakaoSignIn(KakaoSignInRequestDto kakaoSignInRequestDto) {
        KakaoUserInfo userInfo = kakaoSignInRequestDto.fetchUserInfo(kakaoApiService);

        if (userInfo.getEmail() == null || userInfo.getEmail().isBlank()) {
            throw new BaseException(ResponseStatus.INVALID_EMAIL_ADDRESS);
        }

        Member member = findOrCreateKakaoMember(userInfo);
        UserDetails userDetails = new CustomUserDetails(member);

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities()
        );

        return issueTokens (authentication);
    }

    private Member findOrCreateKakaoMember(KakaoUserInfo kakaoUserInfo) {
        return authRepository.findBySignTypeAndSocialId(SignType.KAKAO, kakaoUserInfo.getId())
                .orElseGet(() -> authRepository.save(
                        Member.builder()
                                .signType(SignType.KAKAO)
                                .socialId(kakaoUserInfo.getId())
                                .email(kakaoUserInfo.getEmail())
                                .memberUuid(UuidGenerator.generateUuid())
                                .memberLevel(MemberLevel.WHITE)
                                .build()
                ));
    }
}
