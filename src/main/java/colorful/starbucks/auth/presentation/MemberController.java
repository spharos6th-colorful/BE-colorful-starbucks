package colorful.starbucks.auth.presentation;

import colorful.starbucks.auth.application.MemberService;
import colorful.starbucks.auth.dto.request.*;
import colorful.starbucks.auth.dto.response.AccessTokenResponseDto;
import colorful.starbucks.auth.dto.response.MemberEmailFindResponseDto;
import colorful.starbucks.auth.dto.response.MemberPasswordResetResponseDto;
import colorful.starbucks.auth.vo.request.*;
import colorful.starbucks.auth.vo.response.AccessTokenResponseVo;
import colorful.starbucks.auth.vo.response.MemberEmailFindResponseVo;
import colorful.starbucks.auth.vo.response.MemberPasswordResetResponseVo;
import colorful.starbucks.auth.vo.response.MemberSignInResponseVo;
import colorful.starbucks.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/sign-up")
    public ApiResponse<String> signUp(@RequestBody MemberSignUpRequestVo memberSignUpRequestVo
            ){
        memberService.signUp(MemberSignUpRequestDto.from(memberSignUpRequestVo));
        return ApiResponse.ok("회원가입이 완료되었습니다.",null);
    }

    @GetMapping("/validate/email")
    public ApiResponse<Boolean> checkEmail(@RequestParam String email
    ){
        boolean isDuplicated = memberService.isEmailDuplicated(email);
        return ApiResponse.ok("이메일 중복 체크를 완료하였습니다. ",isDuplicated);
    }

    @PostMapping("/sign-in")
    public ApiResponse<MemberSignInResponseVo> signIn(@RequestBody MemberSignInRequestVo memberSignInRequestVo
    ){
        return ApiResponse.ok(
                "로그인에 성공하였습니다",
                memberService.signIn(MemberSignInRequestDto.from(memberSignInRequestVo)).toVo()
        );
    }

    @PostMapping("/access-token")
    public ApiResponse<AccessTokenResponseVo> refreshToken(@RequestBody RefreshTokenRequestVo refreshTokenRequestVo){

        AccessTokenResponseDto accessTokenResponseDto = memberService.reIssueAccessToken(
                RefreshTokenRequestDto.from(refreshTokenRequestVo)
        );
        AccessTokenResponseVo responseVo = AccessTokenResponseVo.from(accessTokenResponseDto);

        return ApiResponse.ok("accessToken 재발급에 성공하였습니다.",responseVo);
    }

    @PostMapping("/email")
    public ApiResponse<MemberEmailFindResponseVo> findEmail(@RequestBody MemberEmailFindRequestVo memberEmailFindRequestVo) {

        MemberEmailFindRequestDto requestDto = MemberEmailFindRequestDto.from(memberEmailFindRequestVo);

        MemberEmailFindResponseDto responseDto = memberService.findEmail(requestDto);

        MemberEmailFindResponseVo responseVo = responseDto.toVo();

        return ApiResponse.ok("이메일 찾기를 완료하였습니다.",responseVo);
    }

    @PostMapping("/password")
    public ApiResponse<MemberPasswordResetResponseVo> resetPassword(@RequestBody MemberPasswordResetRequestVo memberPasswordResetRequestVo){

        MemberPasswordResetRequestDto requestDto = MemberPasswordResetRequestDto.from(memberPasswordResetRequestVo);

        MemberPasswordResetResponseDto resetResponseDto = memberService.findPassword(requestDto);

        MemberPasswordResetResponseVo responseVo = resetResponseDto.toVo();

        return ApiResponse.ok("비밀번호 찾기를 완료하였습니다.",responseVo);
    }

    @DeleteMapping("/signout")
    public ApiResponse<String> logout() {
        return ApiResponse.ok("로그아웃을 완료했습니다.");
    }

    @PostMapping("/kakao")
    public ApiResponse<MemberSignInResponseVo> kakaoSignIn(@RequestBody KakaoSignInRequestVo kakaoSignInRequestVo
    ){
        return ApiResponse.ok(
                "로그인에 성공하였습니다.",
                memberService.kakaoSignIn(KakaoSignInRequestDto.from(kakaoSignInRequestVo)).toVo()
        );
    }

    //백엔드 카카오 로그인 인가 코드 api
    @GetMapping("/kakao")
    public String kakaoRedirect(@RequestParam String code) {

        System.out.println("카카오 인가코드 = " + code);
        return "OK";
    }





}
