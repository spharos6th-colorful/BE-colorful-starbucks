package colorful.starbucks.auth.application;

import colorful.starbucks.auth.dto.request.MemberSignUpRequestDto;

public interface MemberService {

    void signUp(MemberSignUpRequestDto memberSignUpRequestDto);

    boolean isEmailDuplicated(String email);
}
