package colorful.starbucks.auth.application;

import colorful.starbucks.auth.dto.request.KakaoSignInRequestDto;
import colorful.starbucks.auth.dto.response.MemberSignInResponseDto;

public interface OAuthService {

    MemberSignInResponseDto kakaoSignIn(KakaoSignInRequestDto kakaoSignInRequestDto);
}
