package colorful.starbucks.auth.presentation;

import colorful.starbucks.auth.application.AuthService;
import colorful.starbucks.auth.dto.request.*;
import colorful.starbucks.auth.vo.request.*;
import colorful.starbucks.auth.vo.response.*;
import colorful.starbucks.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(
            summary = "회원 가입 API",
            description = "회원 가입을 위한 API 입니다.",
            tags = {"AUTH-SERVICE"}
    )
    @PostMapping("/sign-up")
    public ApiResponse<String> signUp(@RequestBody MemberSignUpRequestVo memberSignUpRequestVo) {
        authService.signUp(MemberSignUpRequestDto.from(memberSignUpRequestVo));
        return ApiResponse.ok("회원가입이 완료되었습니다.", null);
    }

    @Operation(
            summary = "로그인 API",
            description = "로그인을 위한 API 입니다.",
            tags = {"AUTH-SERVICE"}
    )
    @PostMapping("/sign-in")
    public ApiResponse<MemberSignInResponseVo> signIn(@RequestBody MemberSignInRequestVo memberSignInRequestVo) {
        return ApiResponse.ok(
                "로그인에 성공하였습니다.",
                authService.signIn(MemberSignInRequestDto.from(memberSignInRequestVo)).toVo());
    }

    @Operation(
            summary = "액세스 토큰 재발급 API",
            description = "리프레시 토큰을 이용해 액세스 토큰을 재발급하는 API 입니다.",
            tags = {"AUTH-SERVICE"}
    )
    @PostMapping("/access-token")
    public ApiResponse<AccessTokenResponseVo> refreshToken(@RequestBody RefreshTokenRequestVo refreshTokenRequestVo) {
        return ApiResponse.ok(
                "accessToken 재발급에 성공하였습니다.",
                authService.reIssueAccessToken(RefreshTokenRequestDto.from(refreshTokenRequestVo)).toVo());
    }

    @Operation(
            summary = "로그아웃 API",
            description = "로그아웃을 위한 API 입니다.",
            tags = {"AUTH-SERVICE"}
    )
    @DeleteMapping("/sign-out")
    public ApiResponse<Void> signOut(@RequestBody MemberSignOutRequestDto memberSignOutRequestDto) {
        authService.signOut(memberSignOutRequestDto);
        return ApiResponse.ok("로그아웃을 완료했습니다",null);
    }







}
