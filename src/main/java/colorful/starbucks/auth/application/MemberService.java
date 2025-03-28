package colorful.starbucks.auth.application;

import colorful.starbucks.auth.dto.request.MemberSignInRequestDto;
import colorful.starbucks.auth.dto.request.MemberSignUpRequestDto;
import colorful.starbucks.auth.dto.response.MemberSignInResponseDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface MemberService {

    void signUp(MemberSignUpRequestDto memberSignUpRequestDto);

    boolean isEmailDuplicated(String email);

    UserDetails loadUserByUsername(String memberUuid);

    MemberSignInResponseDto signIn(MemberSignInRequestDto memberSignInRequestDto);
}
