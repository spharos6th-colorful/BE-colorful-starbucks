package colorful.starbucks.auth.presentation;

import colorful.starbucks.auth.application.MemberService;
import colorful.starbucks.auth.dto.request.*;
import colorful.starbucks.auth.vo.request.*;
import colorful.starbucks.auth.vo.response.AccessTokenResponseVo;
import colorful.starbucks.auth.vo.response.MemberEmailFindResponseVo;
import colorful.starbucks.auth.vo.response.MemberPasswordResetResponseVo;
import colorful.starbucks.auth.vo.response.MemberSignInResponseVo;
import colorful.starbucks.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/sign-up")
    public ApiResponse<String> signUp(@RequestBody MemberSignUpRequestVo vo) {
        memberService.signUp(MemberSignUpRequestDto.from(vo));
        return ApiResponse.ok("회원가입이 완료되었습니다.", null);
    }

    @GetMapping("/validate/email")
    public ApiResponse<Boolean> checkEmail(@RequestParam String email) {
        return ApiResponse.ok(
                "이메일 중복 체크를 완료하였습니다.",
                memberService.isEmailDuplicated(email));
    }

    @PostMapping("/sign-in")
    public ApiResponse<MemberSignInResponseVo> signIn(@RequestBody MemberSignInRequestVo vo) {
        return ApiResponse.ok(
                "로그인에 성공하였습니다.",
                memberService.signIn(MemberSignInRequestDto.from(vo)).toVo());
    }

    @PostMapping("/access-token")
    public ApiResponse<AccessTokenResponseVo> refreshToken(@RequestBody RefreshTokenRequestVo vo) {
        return ApiResponse.ok(
                "accessToken 재발급에 성공하였습니다.",
                memberService.reIssueAccessToken(RefreshTokenRequestDto.from(vo)).toVo());
    }

    @PostMapping("/email")
    public ApiResponse<MemberEmailFindResponseVo> findEmail(@RequestBody MemberEmailFindRequestVo vo) {
        return ApiResponse.ok(
                "이메일 찾기를 완료하였습니다.",
                memberService.findEmail(MemberEmailFindRequestDto.from(vo)).toVo());
    }

    @PostMapping("/password")
    public ApiResponse<MemberPasswordResetResponseVo> resetPassword(@RequestBody MemberPasswordResetRequestVo vo) {
        return ApiResponse.ok(
                "비밀번호 찾기를 완료하였습니다.",
                memberService.findPassword(MemberPasswordResetRequestDto.from(vo)).toVo());
    }

    @DeleteMapping("/signout")
    public ApiResponse<String> logout() {
        return ApiResponse.ok("로그아웃을 완료했습니다.");
    }

    @PostMapping("/kakao")
    public ApiResponse<MemberSignInResponseVo> kakaoSignIn(@RequestBody KakaoSignInRequestVo vo) {
        return ApiResponse.ok(
                "로그인에 성공하였습니다.",
                memberService.kakaoSignIn(KakaoSignInRequestDto.from(vo)).toVo());
    }

    //백엔드 카카오 로그인 인가 코드 api
    @GetMapping("/kakao")
    public String kakaoRedirect(@RequestParam String code) {

        System.out.println("카카오 인가코드 = " + code);
        return "OK";
    }
}
