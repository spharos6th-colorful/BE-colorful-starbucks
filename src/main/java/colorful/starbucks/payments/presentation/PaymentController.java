package colorful.starbucks.payments.presentation;

import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.payments.application.PaymentsService;
import colorful.starbucks.payments.domain.PaymentHistory;
import colorful.starbucks.payments.dto.request.TossPaymentCancelRequestDto;
import colorful.starbucks.payments.dto.request.TossPaymentRequestDto;
import colorful.starbucks.payments.dto.response.PaymentHistoryResponseDto;
import colorful.starbucks.payments.dto.response.TossPaymentCancelResponseDto;
import colorful.starbucks.payments.dto.response.TossPaymentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentsService paymentsService;

    @PostMapping("/toss")
    public ApiResponse<TossPaymentResponseDto> approveToss(Authentication authentication,
                                                           @RequestBody TossPaymentRequestDto tossPaymentRequestDto) {
        String memberUuid = authentication.getName();

        return ApiResponse.ok(
                "결제 요청이 성공적으로 처리되었습니다.",
                paymentsService.approveTossPayment(
                        TossPaymentRequestDto.of(tossPaymentRequestDto, memberUuid)
                )
        );
    }

    @PostMapping("/toss/cancel")
    public ApiResponse<TossPaymentCancelResponseDto> cancelTossPayment(Authentication authentication,
                                                                       @RequestBody TossPaymentCancelRequestDto tossPaymentCancelRequestDto
    ) {
        String memberUuid = authentication.getName();

        return ApiResponse.ok(
                "결제 취소 요청이 성공적으로 처리되었습니다.",
                paymentsService.cancelTossPayment(
                        TossPaymentCancelRequestDto.of(tossPaymentCancelRequestDto, memberUuid)
                )
        );
    }

    @GetMapping("/history")
    public ApiResponse<List<PaymentHistoryResponseDto>> getPaymentHistory(Authentication authentication) {
        return ApiResponse.ok(
                "결제 내역 조회를 완료하였습니다.",
                paymentsService.getPaymentHistory(authentication.getName())
        );
    }

    @GetMapping("/history/{orderCode}")
    public ApiResponse<List<PaymentHistoryResponseDto>> getPaymentHistoryByOrderCode(
            @PathVariable Long orderCode,
            Authentication authentication
    ) {
        return ApiResponse.ok(
                "결제 내역 조회를 완료하였습니다.",
                paymentsService.getPaymentHistoryByOrderCode(orderCode, authentication.getName())
        );
    }
}
