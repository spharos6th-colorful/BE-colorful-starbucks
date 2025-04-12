package colorful.starbucks.payments.application;

import colorful.starbucks.payments.dto.request.TossPaymentCancelRequestDto;
import colorful.starbucks.payments.dto.request.TossPaymentRequestDto;
import colorful.starbucks.payments.dto.response.TossPaymentCancelResponseDto;
import colorful.starbucks.payments.dto.response.TossPaymentResponseDto;

public interface PaymentsService {

    TossPaymentResponseDto approveTossPayment(TossPaymentRequestDto tossPaymentRequestDto);

    TossPaymentCancelResponseDto cancelTossPayment(TossPaymentCancelRequestDto tossPaymentCancelRequestDto);

}
