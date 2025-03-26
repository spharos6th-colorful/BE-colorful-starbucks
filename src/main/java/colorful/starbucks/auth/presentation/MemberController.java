package colorful.starbucks.auth.presentation;

import colorful.starbucks.auth.application.MemberService;
import colorful.starbucks.auth.dto.request.MemberSignUpRequestDto;
import colorful.starbucks.auth.vo.request.MemberSignUpRequestVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/auth")
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/sign-up")
    public void signUp(
            @RequestBody MemberSignUpRequestVo memberSignUpRequestVo
            ){
        memberService.signUp(MemberSignUpRequestDto.from(memberSignUpRequestVo));
    }


}
