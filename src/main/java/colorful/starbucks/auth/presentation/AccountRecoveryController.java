package colorful.starbucks.auth.presentation;

import colorful.starbucks.auth.application.AccountRecoveryService;
import colorful.starbucks.auth.dto.request.MemberEmailFindRequestDto;
import colorful.starbucks.auth.dto.request.MemberPasswordResetRequestDto;
import colorful.starbucks.auth.vo.request.MemberEmailFindRequestVo;
import colorful.starbucks.auth.vo.request.MemberPasswordResetRequestVo;
import colorful.starbucks.auth.vo.response.MemberEmailFindResponseVo;
import colorful.starbucks.auth.vo.response.MemberPasswordResetResponseVo;
import colorful.starbucks.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AccountRecoveryController {

    private final AccountRecoveryService accountRecoveryService;

    @PostMapping("/email")
    public ApiResponse<MemberEmailFindResponseVo> findEmail(@RequestBody MemberEmailFindRequestVo memberEmailFindRequestVo) {
        return ApiResponse.ok(
                "이메일 찾기를 완료하였습니다.",
                accountRecoveryService.findEmail(MemberEmailFindRequestDto.from(memberEmailFindRequestVo)).toVo());
    }

    @PostMapping("/password")
    public ApiResponse<MemberPasswordResetResponseVo> resetPassword(@RequestBody MemberPasswordResetRequestVo memberPasswordResetRequestVo) {
        return ApiResponse.ok(
                "비밀번호 찾기를 완료하였습니다.",
                accountRecoveryService.findPassword(MemberPasswordResetRequestDto.from(memberPasswordResetRequestVo)).toVo());
    }

    @GetMapping("/email/exists")
    public ApiResponse<Boolean> checkEmailDuplication(@RequestParam String email) {
        return ApiResponse.ok(
                "이메일 중복 체크를 완료하였습니다.",
                accountRecoveryService.isEmailDuplicated(email));
    }
}
