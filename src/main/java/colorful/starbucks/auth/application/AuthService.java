package colorful.starbucks.auth.application;

import colorful.starbucks.auth.dto.request.*;
import colorful.starbucks.auth.dto.response.*;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {

    void signUp(MemberSignUpRequestDto memberSignUpRequestDto);

    UserDetails loadUserByUuid(String uuid);

    MemberSignInResponseDto signIn(MemberSignInRequestDto memberSignInRequestDto);

    AccessTokenResponseDto reIssueAccessToken(RefreshTokenRequestDto refreshTokenRequestDto);

    void signOut(MemberSignOutRequestDto memberSignOutRequestDto);


}
