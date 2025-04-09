package colorful.starbucks.auth.application;

import colorful.starbucks.auth.dto.request.EmailCodeSendRequestDto;
import colorful.starbucks.auth.dto.request.EmailVerifyCodeRequestDto;
import colorful.starbucks.auth.dto.response.EmailCodeSendResponseDto;
import colorful.starbucks.common.exception.BaseException;
import colorful.starbucks.common.response.ResponseStatus;
import colorful.starbucks.common.service.EmailSendService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmailServiceImpl implements EmailService {

    private final EmailAuthRedisService emailAuthRedisService;
    private final EmailSendService emailSendService;


    @Transactional
    @Override
    public EmailCodeSendResponseDto sendEmail(EmailCodeSendRequestDto emailCodeSendRequestDto) {
        String code = emailCodeSendRequestDto.codeGenerator();
        emailAuthRedisService.saveCode(emailCodeSendRequestDto.getEmail(), code);
        emailSendService.sendEmailCode(emailCodeSendRequestDto.getEmail(), code);
        return EmailCodeSendResponseDto.from(code);
    }

    @Transactional
    @Override
    public void verifyEmailCode(EmailVerifyCodeRequestDto emailVerifyCodeRequestDto) {
        if (emailAuthRedisService.verifyCode(emailVerifyCodeRequestDto.getEmail(), emailVerifyCodeRequestDto.getCode())) {
            emailAuthRedisService.deleteCode(emailVerifyCodeRequestDto.getEmail());
        } else {
            throw new BaseException(ResponseStatus.INVALID_AUTH_CODE);
        }
    }
}
