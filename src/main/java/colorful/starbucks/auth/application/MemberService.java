package colorful.starbucks.auth.application;

import colorful.starbucks.auth.dto.request.MemberSignUpRequestDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface MemberService {

    void signUp(MemberSignUpRequestDto memberSignUpRequestDto);

    boolean isEmailDuplicated(String email);

    UserDetails loadUserByUsername(String memberUuid);
}
