package colorful.starbucks.common.exception;

import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.common.response.ResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class BaseExceptionHandler {
    /**
     * 발생한 예외 처리
     */
    @ExceptionHandler(BaseException.class)
    protected ApiResponse<Void> BaseError(BaseException e) {
        log.error("BaseException -> {} ({})", e.getStatus(), e.getMessage());
        return ApiResponse.error(e.getStatus());
    }

    /**
     * security 인증 에러
     * 아이디가 없거나 비밀번호가 틀린 경우 AuthenticationManager 에서 발생
     *
     * @return FAILED_TO_LOGIN 에러 response
     */
    @ExceptionHandler(BadCredentialsException.class)
    protected ApiResponse<Void> handleBadCredentialsException(BadCredentialsException e) {
        log.error("BadCredentialsException: ", e);
        return ApiResponse.error(ResponseStatus.FAILED_TO_LOGIN);
    }

    @ExceptionHandler(RuntimeException.class)
    protected ApiResponse<Void> RuntimeError(RuntimeException e) {
        log.error("RuntimeException: ", e);
        for (StackTraceElement s : e.getStackTrace()) {
            System.out.println(s);
        }
        return ApiResponse.error(ResponseStatus.INTERNAL_SERVER_ERROR);
    }
}
