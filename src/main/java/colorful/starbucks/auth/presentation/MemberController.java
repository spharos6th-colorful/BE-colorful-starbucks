package colorful.starbucks.auth.presentation;

import colorful.starbucks.auth.application.MemberService;
import colorful.starbucks.auth.dto.request.*;
import colorful.starbucks.auth.vo.request.*;
import colorful.starbucks.auth.vo.response.*;
import colorful.starbucks.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/sign-up")
    public ApiResponse<String> signUp(@RequestBody MemberSignUpRequestVo memberSignUpRequestVo) {
        memberService.signUp(MemberSignUpRequestDto.from(memberSignUpRequestVo));
        return ApiResponse.ok("회원가입이 완료되었습니다.", null);
    }

    @GetMapping("/email/exists")
    public ApiResponse<Boolean> checkEmailDuplication(@RequestParam String email) {
        return ApiResponse.ok(
                "이메일 중복 체크를 완료하였습니다.",
                memberService.isEmailDuplicated(email));
    }

    @PostMapping("/sign-in")
    public ApiResponse<MemberSignInResponseVo> signIn(@RequestBody MemberSignInRequestVo memberSignInRequestVo) {
        return ApiResponse.ok(
                "로그인에 성공하였습니다.",
                memberService.signIn(MemberSignInRequestDto.from(memberSignInRequestVo)).toVo());
    }

    @PostMapping("/access-token")
    public ApiResponse<AccessTokenResponseVo> refreshToken(@RequestBody RefreshTokenRequestVo refreshTokenRequestVo) {
        return ApiResponse.ok(
                "accessToken 재발급에 성공하였습니다.",
                memberService.reIssueAccessToken(RefreshTokenRequestDto.from(refreshTokenRequestVo)).toVo());
    }

    @PostMapping("/email")
    public ApiResponse<MemberEmailFindResponseVo> findEmail(@RequestBody MemberEmailFindRequestVo memberEmailFindRequestVo) {
        return ApiResponse.ok(
                "이메일 찾기를 완료하였습니다.",
                memberService.findEmail(MemberEmailFindRequestDto.from(memberEmailFindRequestVo)).toVo());
    }

    @PostMapping("/password")
    public ApiResponse<MemberPasswordResetResponseVo> resetPassword(@RequestBody MemberPasswordResetRequestVo memberPasswordResetRequestVo) {
        return ApiResponse.ok(
                "비밀번호 찾기를 완료하였습니다.",
                memberService.findPassword(MemberPasswordResetRequestDto.from(memberPasswordResetRequestVo)).toVo());
    }

    @DeleteMapping("/sign-out")
    public ApiResponse<String> logout() {
        return ApiResponse.ok("로그아웃을 완료했습니다.");
    }

    @PostMapping("/kakao")
    public ApiResponse<MemberSignInResponseVo> kakaoSignIn(@RequestBody KakaoSignInRequestVo kakaoSignInRequestVo) {
        return ApiResponse.ok(
                "로그인에 성공하였습니다.",
                memberService.kakaoSignIn(KakaoSignInRequestDto.from(kakaoSignInRequestVo)).toVo());
    }

    @PostMapping("/email/send-code")
    public ApiResponse<EmailCodeSendResponseVo> sendEmail(@RequestBody EmailCodeSendRequestVo emailCodeSendRequestVo) {
        return ApiResponse.ok(
                "이메일 인증 번호를 전송하였습니다",
                memberService.sendEmail(EmailCodeSendRequestDto.from(emailCodeSendRequestVo)).toVo()
        );
    }

    @PostMapping("/email/verify-code")
    public ApiResponse<String> verifyEmailCode(@RequestBody EmailVerifyCodeRequestVo emailVerifyCodeRequestVo) {
        memberService.verifyEmailCode(EmailVerifyCodeRequestDto.from(emailVerifyCodeRequestVo));
        return ApiResponse.ok("이메일 인증이 완료되었습니다.");
    }




    //TODO 백엔드 카카오 로그인 인가 코드 api
    @GetMapping("/kakao")
    public String kakaoRedirect(@RequestParam String code) {

        System.out.println("카카오 인가코드 = " + code);
        return "OK";
    }
}
