package colorful.starbucks.payments.presentation;

import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.payments.application.PaymentsService;
import colorful.starbucks.payments.dto.request.TossPaymentRequestDto;
import colorful.starbucks.payments.dto.response.TossPaymentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentsService paymentsService;

    @PostMapping("/toss")
    public ApiResponse<TossPaymentResponseDto> approveTossPayment(
            Authentication authentication,
            @RequestBody TossPaymentRequestDto tossPaymentRequestDto
    ) {
        return ApiResponse.ok(
                "결제 요청이 성공적으로 처리되었습니다.",
                paymentsService.approveTossPayment(tossPaymentRequestDto)
        );
    }
}
