package colorful.starbucks.auth.application;

import colorful.starbucks.auth.dto.request.OAuthSignInRequestDto;
import colorful.starbucks.auth.dto.response.OAutSignInResponseDto;

public interface OAuthService {

    OAutSignInResponseDto kakaoSignIn(OAuthSignInRequestDto OAuthSignInRequestDto);
}
