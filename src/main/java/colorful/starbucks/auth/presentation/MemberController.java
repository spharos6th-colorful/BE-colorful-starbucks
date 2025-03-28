package colorful.starbucks.auth.presentation;

import colorful.starbucks.auth.application.MemberService;
import colorful.starbucks.auth.dto.request.MemberSignInRequestDto;
import colorful.starbucks.auth.dto.request.MemberSignUpRequestDto;
import colorful.starbucks.auth.dto.response.MemberSignInResponseDto;
import colorful.starbucks.auth.vo.request.MemberSignInRequestVo;
import colorful.starbucks.auth.vo.request.MemberSignUpRequestVo;
import colorful.starbucks.auth.vo.response.MemberSignInResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/email-check")
    public ResponseEntity<Boolean> checkEmail(
            @RequestParam String email
    ){
        boolean isDuplicated = memberService.isEmailDuplicated(email);
        return ResponseEntity.ok(isDuplicated);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<MemberSignInResponseVo> signIn(
            @RequestBody MemberSignInRequestVo memberSignInRequestVo
    ){
        MemberSignInResponseDto dto = memberService.signIn(MemberSignInRequestDto.from(memberSignInRequestVo));
        return ResponseEntity.ok(dto.toMemberSignInResponseVo());
    }


}
