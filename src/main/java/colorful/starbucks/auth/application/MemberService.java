package colorful.starbucks.auth.application;

import colorful.starbucks.auth.dto.request.MemberEmailFindRequestDto;
import colorful.starbucks.auth.dto.request.MemberSignInRequestDto;
import colorful.starbucks.auth.dto.request.MemberSignUpRequestDto;
import colorful.starbucks.auth.dto.request.RefreshTokenRequestDto;
import colorful.starbucks.auth.dto.response.AccessTokenResponseDto;
import colorful.starbucks.auth.dto.response.MemberEmailFindResponseDto;
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

}
