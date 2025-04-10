package colorful.starbucks.auth.application;

import colorful.starbucks.auth.dto.request.OAuthSignInRequestDto;
import colorful.starbucks.auth.dto.response.OAuthSignInResponseDto;

public interface OAuthService {

    OAuthSignInResponseDto kakaoSignIn(OAuthSignInRequestDto OAuthSignInRequestDto);
}
