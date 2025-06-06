package colorful.starbucks.auth.presentation;

import colorful.starbucks.auth.application.OAuthService;
import colorful.starbucks.auth.dto.request.OAuthSignInRequestDto;
import colorful.starbucks.auth.vo.request.OAuthSignInRequestVo;
import colorful.starbucks.auth.vo.response.OAutSignInResponseVo;
import colorful.starbucks.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class OAuthController {
    private final OAuthService oAuthService;

    @Operation(
            summary = "카카오 로그인 API",
            description = "카카오 로그인 API 입니다.",
            tags = {"AUTH-SERVICE"}
    )
    @PostMapping("/kakao")
    public ApiResponse<OAutSignInResponseVo> kakaoSignIn(@RequestBody OAuthSignInRequestVo OAuthSignInRequestVo) {
        return ApiResponse.ok(
                "로그인에 성공하였습니다.",
                oAuthService.kakaoSignIn(OAuthSignInRequestDto.from(OAuthSignInRequestVo)).toVo());
    }
}
