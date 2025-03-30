package colorful.starbucks.auth.presentation;

import colorful.starbucks.auth.application.MemberService;
import colorful.starbucks.auth.dto.request.*;
import colorful.starbucks.auth.dto.response.AccessTokenResponseDto;
import colorful.starbucks.auth.dto.response.MemberEmailFindResponseDto;
import colorful.starbucks.auth.dto.response.MemberPasswordResetResponseDto;
import colorful.starbucks.auth.dto.response.MemberSignInResponseDto;
import colorful.starbucks.auth.vo.request.*;
import colorful.starbucks.auth.vo.response.AccessTokenResponseVo;
import colorful.starbucks.auth.vo.response.MemberEmailFindResponseVo;
import colorful.starbucks.auth.vo.response.MemberPasswordResetResponseVo;
import colorful.starbucks.auth.vo.response.MemberSignInResponseVo;
import colorful.starbucks.common.response.ApiResponse;
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

    @GetMapping("/validate/email")
    public ResponseEntity<Boolean> checkEmail(
            @RequestParam String email
    ){
        boolean isDuplicated = memberService.isEmailDuplicated(email);
        return ResponseEntity.ok(isDuplicated);
    }

    @PostMapping("/sign-in")
    public ApiResponse<MemberSignInResponseVo> signIn(
            @RequestBody MemberSignInRequestVo memberSignInRequestVo
    ){
        return ApiResponse.ok(
                memberService.signIn(MemberSignInRequestDto.from(memberSignInRequestVo)).toVo()
        );
    }

    @PostMapping("/access-token")
    public ApiResponse<AccessTokenResponseVo> refreshToken(
            @RequestBody RefreshTokenRequestVo refreshTokenRequestVo){

        AccessTokenResponseDto accessTokenResponseDto = memberService.reIssueAccessToken(
                RefreshTokenRequestDto.from(refreshTokenRequestVo)
        );
        AccessTokenResponseVo responseVo = AccessTokenResponseVo.from(accessTokenResponseDto);

        return ApiResponse.ok(responseVo);
    }

    @PostMapping("/email")
    public ApiResponse<MemberEmailFindResponseVo> emailFind(
            @RequestBody MemberEmailFindRequestVo memberEmailFindRequestVo) {

        MemberEmailFindRequestDto requestDto = MemberEmailFindRequestDto.from(memberEmailFindRequestVo);

        MemberEmailFindResponseDto responseDto = memberService.findEmail(requestDto);

        MemberEmailFindResponseVo responseVo = responseDto.toVo();

        return ApiResponse.ok(responseVo);
    }

    @PostMapping("/password")
    public ApiResponse<MemberPasswordResetResponseVo> passwordReset(
            @RequestBody MemberPasswordResetRequestVo memberPasswordResetRequestVo){

        MemberPasswordResetRequestDto requestDto = MemberPasswordResetRequestDto.from(memberPasswordResetRequestVo);

        MemberPasswordResetResponseDto resetResponseDto = memberService.findPassword(requestDto);

        MemberPasswordResetResponseVo responseVo = resetResponseDto.toVo();

        return ApiResponse.ok(responseVo);
    }




}
