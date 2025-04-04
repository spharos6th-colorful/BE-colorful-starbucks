package colorful.starbucks.auth.application;

import colorful.starbucks.auth.dto.request.*;
import colorful.starbucks.auth.dto.response.*;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {

    void signUp(MemberSignUpRequestDto memberSignUpRequestDto);

    boolean isEmailDuplicated(String email);

    UserDetails loadUserByUuid(String uuid);

    MemberSignInResponseDto signIn(MemberSignInRequestDto memberSignInRequestDto);

    AccessTokenResponseDto reIssueAccessToken(RefreshTokenRequestDto refreshTokenRequestDto);

    MemberEmailFindResponseDto findEmail(MemberEmailFindRequestDto memberEmailFindRequestDto);

    MemberPasswordResetResponseDto findPassword(MemberPasswordResetRequestDto memberPasswordResetRequestDto);

    MemberSignInResponseDto kakaoSignIn(KakaoSignInRequestDto kakaoSignInRequestDto);

    EmailCodeSendResponseDto sendEmail(EmailCodeSendRequestDto emailCodeSendRequestDto);

    void verifyEmailCode(EmailVerifyCodeRequestDto emailVerifyCodeRequestDto);

    void signOut(MemberSignOutRequestDto memberSignOutRequestDto);


}
