package colorful.starbucks.auth.presentation;

import colorful.starbucks.auth.application.EmailService;
import colorful.starbucks.auth.dto.request.EmailCodeSendRequestDto;
import colorful.starbucks.auth.dto.request.EmailVerifyCodeRequestDto;
import colorful.starbucks.auth.vo.request.EmailCodeSendRequestVo;
import colorful.starbucks.auth.vo.request.EmailVerifyCodeRequestVo;
import colorful.starbucks.auth.vo.response.EmailCodeSendResponseVo;
import colorful.starbucks.common.response.ApiResponse;
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



    @PostMapping("/email/send-code")
    public ApiResponse<EmailCodeSendResponseVo> sendEmail(@RequestBody EmailCodeSendRequestVo emailCodeSendRequestVo) {
        return ApiResponse.ok(
                "이메일 인증 번호를 전송하였습니다",
                emailService.sendEmail(EmailCodeSendRequestDto.from(emailCodeSendRequestVo)).toVo()
        );
    }

    @PostMapping("/email/verify-code")
    public ApiResponse<String> verifyEmailCode(@RequestBody EmailVerifyCodeRequestVo emailVerifyCodeRequestVo) {
        emailService.verifyEmailCode(EmailVerifyCodeRequestDto.from(emailVerifyCodeRequestVo));
        return ApiResponse.ok("이메일 인증이 완료되었습니다.");
    }
}
