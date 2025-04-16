package colorful.starbucks.auth.presentation;

import colorful.starbucks.auth.application.EmailService;
import colorful.starbucks.auth.dto.request.EmailCodeSendRequestDto;
import colorful.starbucks.auth.dto.request.EmailVerifyCodeRequestDto;
import colorful.starbucks.auth.vo.request.EmailCodeSendRequestVo;
import colorful.starbucks.auth.vo.request.EmailVerifyCodeRequestVo;
import colorful.starbucks.auth.vo.response.EmailCodeSendResponseVo;
import colorful.starbucks.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @Operation(
            summary = "인증 번호 전송 API",
            description = "인증 번호를 이메일로 전송하는 API 입니다.",
            tags = {"AUTH-SERVICE"}
    )
    @PostMapping("/email/send-code")
    public ApiResponse<EmailCodeSendResponseVo> sendEmail(@RequestBody EmailCodeSendRequestVo emailCodeSendRequestVo) {
        return ApiResponse.ok(
                "이메일 인증 번호를 전송하였습니다",
                emailService.sendEmail(EmailCodeSendRequestDto.from(emailCodeSendRequestVo)).toVo()
        );
    }

    @Operation(
            summary = "이메일 인증 확인 API",
            description = "이메일 인증 번호를 확인하는 API 입니다.",
            tags = {"AUTH-SERVICE"}
    )
    @PostMapping("/email/verify-code")
    public ApiResponse<String> verifyEmailCode(@RequestBody EmailVerifyCodeRequestVo emailVerifyCodeRequestVo) {
        emailService.verifyEmailCode(EmailVerifyCodeRequestDto.from(emailVerifyCodeRequestVo));
        return ApiResponse.ok("이메일 인증이 완료되었습니다.");
    }
}
