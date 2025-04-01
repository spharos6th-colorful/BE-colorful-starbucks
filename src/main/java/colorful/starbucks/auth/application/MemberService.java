package colorful.starbucks.auth.application;

import colorful.starbucks.auth.dto.request.*;
import colorful.starbucks.auth.dto.response.AccessTokenResponseDto;
import colorful.starbucks.auth.dto.response.MemberEmailFindResponseDto;
import colorful.starbucks.auth.dto.response.MemberPasswordResetResponseDto;
import colorful.starbucks.auth.dto.response.MemberSignInResponseDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface MemberService {

    void signUp(MemberSignUpRequestDto memberSignUpRequestDto);

    boolean isEmailDuplicated(String email);

    UserDetails loadUserByUsername(String email);

    UserDetails loadUserByUuid(String uuid);

    MemberSignInResponseDto signIn(MemberSignInRequestDto memberSignInRequestDto);

    AccessTokenResponseDto reIssueAccessToken(RefreshTokenRequestDto refreshTokenRequestDto);

    MemberEmailFindResponseDto findEmail(MemberEmailFindRequestDto memberEmailFindRequestDto);

    MemberPasswordResetResponseDto findPassword(MemberPasswordResetRequestDto memberPasswordResetRequestDto);

    MemberSignInResponseDto kakaoSignIn(KakaoSignInRequestDto kakaoSignInRequestDto);

}
