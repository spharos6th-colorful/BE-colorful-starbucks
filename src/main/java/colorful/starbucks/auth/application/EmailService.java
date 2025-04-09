package colorful.starbucks.auth.application;

import colorful.starbucks.auth.dto.request.EmailCodeSendRequestDto;
import colorful.starbucks.auth.dto.request.EmailVerifyCodeRequestDto;
import colorful.starbucks.auth.dto.response.EmailCodeSendResponseDto;

public interface EmailService {

    EmailCodeSendResponseDto sendEmail(EmailCodeSendRequestDto emailCodeSendRequestDto);

    void verifyEmailCode(EmailVerifyCodeRequestDto emailVerifyCodeRequestDto);

}
