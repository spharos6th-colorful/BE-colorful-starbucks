package colorful.starbucks.auth.presentation;

import colorful.starbucks.auth.application.OAuthService;
import colorful.starbucks.auth.dto.request.KakaoSignInRequestDto;
import colorful.starbucks.auth.vo.request.KakaoSignInRequestVo;
import colorful.starbucks.auth.vo.response.MemberSignInResponseVo;
import colorful.starbucks.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class OAuthController {
    private final OAuthService oAuthService;

    @PostMapping("/kakao")
    public ApiResponse<MemberSignInResponseVo> kakaoSignIn(@RequestBody KakaoSignInRequestVo kakaoSignInRequestVo) {
        return ApiResponse.ok(
                "로그인에 성공하였습니다.",
                oAuthService.kakaoSignIn(KakaoSignInRequestDto.from(kakaoSignInRequestVo)).toVo());
    }


    //TODO 백엔드 카카오 로그인 인가 코드 api
    @GetMapping("/kakao")
    public String kakaoRedirect(@RequestParam String code) {

        System.out.println("카카오 인가코드 = " + code);
        return "OK";
    }
}
