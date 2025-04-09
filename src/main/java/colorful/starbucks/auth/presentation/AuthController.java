package colorful.starbucks.auth.presentation;

import colorful.starbucks.auth.application.AuthService;
import colorful.starbucks.auth.dto.request.*;
import colorful.starbucks.auth.vo.request.*;
import colorful.starbucks.auth.vo.response.*;
import colorful.starbucks.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public ApiResponse<String> signUp(@RequestBody MemberSignUpRequestVo memberSignUpRequestVo) {
        authService.signUp(MemberSignUpRequestDto.from(memberSignUpRequestVo));
        return ApiResponse.ok("회원가입이 완료되었습니다.", null);
    }



    @PostMapping("/sign-in")
    public ApiResponse<MemberSignInResponseVo> signIn(@RequestBody MemberSignInRequestVo memberSignInRequestVo) {
        return ApiResponse.ok(
                "로그인에 성공하였습니다.",
                authService.signIn(MemberSignInRequestDto.from(memberSignInRequestVo)).toVo());
    }

    @PostMapping("/access-token")
    public ApiResponse<AccessTokenResponseVo> refreshToken(@RequestBody RefreshTokenRequestVo refreshTokenRequestVo) {
        return ApiResponse.ok(
                "accessToken 재발급에 성공하였습니다.",
                authService.reIssueAccessToken(RefreshTokenRequestDto.from(refreshTokenRequestVo)).toVo());
    }

    @DeleteMapping("/sign-out")
    public ApiResponse<Void> signOut(@RequestBody MemberSignOutRequestDto dto) {
        authService.signOut(dto);
        return ApiResponse.ok("로그아웃을 완료했습니다",null);
    }







}
