package colorful.starbucks.auth.presentation;

import colorful.starbucks.auth.application.AccountRecoveryService;
import colorful.starbucks.auth.dto.request.MemberEmailFindRequestDto;
import colorful.starbucks.auth.dto.request.MemberPasswordResetRequestDto;
import colorful.starbucks.auth.vo.request.MemberEmailFindRequestVo;
import colorful.starbucks.auth.vo.request.MemberPasswordResetRequestVo;
import colorful.starbucks.auth.vo.response.MemberEmailFindResponseVo;
import colorful.starbucks.auth.vo.response.MemberPasswordResetResponseVo;
import colorful.starbucks.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AccountRecoveryController {

    private final AccountRecoveryService accountRecoveryService;

    @Operation(
            summary = "사용자 이메일 찾기 API",
            description = "사용자 정보를 이용해 이메일을 찾는 API 입니다.",
            tags = {"AUTH-SERVICE"}
    )
    @PostMapping("/email")
    public ApiResponse<MemberEmailFindResponseVo> findEmail(@RequestBody MemberEmailFindRequestVo memberEmailFindRequestVo) {
        return ApiResponse.ok(
                "이메일 찾기를 완료하였습니다.",
                accountRecoveryService.findEmail(MemberEmailFindRequestDto.from(memberEmailFindRequestVo)).toVo());
    }

    @Operation(
            summary = "사용자 비밀번호 찾기 API",
            description = "사용자 정보를 확인하고 비밀번호를 초기화하고 이메일을 전송하는 API 입니다.",
            tags = {"AUTH-SERVICE"}
    )
    @PostMapping("/password")
    public ApiResponse<MemberPasswordResetResponseVo> resetPassword(@RequestBody MemberPasswordResetRequestVo memberPasswordResetRequestVo) {
        return ApiResponse.ok(
                "비밀번호 찾기를 완료하였습니다.",
                accountRecoveryService.findPassword(MemberPasswordResetRequestDto.from(memberPasswordResetRequestVo)).toVo());
    }

    @Operation(
            summary = "사용자 이메일 중복 확인 API",
            description = "사용자 이메일 중복 확인을 위한 API 입니다.",
            tags = {"AUTH-SERVICE"}
    )
    @GetMapping("/email/exists")
    public ApiResponse<Boolean> checkEmailDuplication(@RequestParam String email) {
        return ApiResponse.ok(
                "이메일 중복 체크를 완료하였습니다.",
                accountRecoveryService.isEmailDuplicated(email));
    }
}
