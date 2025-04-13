package colorful.starbucks.payments.application;

import colorful.starbucks.payments.dto.request.TossPaymentCancelRequestDto;
import colorful.starbucks.payments.dto.request.TossPaymentRequestDto;
import colorful.starbucks.payments.dto.response.PaymentHistoryResponseDto;
import colorful.starbucks.payments.dto.response.TossPaymentCancelResponseDto;
import colorful.starbucks.payments.dto.response.TossPaymentResponseDto;

import java.util.List;

public interface PaymentsService {

    TossPaymentResponseDto approveTossPayment(TossPaymentRequestDto tossPaymentRequestDto, String memberUuid);

    TossPaymentCancelResponseDto cancelTossPayment(TossPaymentCancelRequestDto tossPaymentCancelRequestDto, String memberUuid);

    List<PaymentHistoryResponseDto> getPaymentHistory(String memberUuid);

    List<PaymentHistoryResponseDto> getPaymentHistoryByOrderCode(String orderCode, String memberUuid);



}
