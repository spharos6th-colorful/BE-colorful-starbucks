package colorful.starbucks.payments.application;

import colorful.starbucks.payments.dto.request.TossPaymentCancelRequestDto;
import colorful.starbucks.payments.dto.request.TossPaymentRequestDto;
import colorful.starbucks.payments.dto.response.TossPaymentCancelResponseDto;
import colorful.starbucks.payments.dto.response.TossPaymentResponseDto;
import colorful.starbucks.payments.vo.response.TossPaymentCancelResponseVo;
import colorful.starbucks.payments.vo.response.TossPaymentResponseVo;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PaymentsServiceImpl implements PaymentsService {

    private final TossPaymentsApiService tossPaymentsApiService;

    @Transactional
    @Override
    public TossPaymentResponseDto approveTossPayment(TossPaymentRequestDto tossPaymentRequestDto) {
        JSONObject json = new JSONObject(tossPaymentsApiService.approvePayment(
                tossPaymentRequestDto.getPaymentKey(),
                tossPaymentRequestDto.getOrderId(),
                tossPaymentRequestDto.getAmount()
        ));

        TossPaymentResponseVo tossPaymentResponseVo = TossPaymentResponseVo.builder()
                .paymentKey(json.optString("paymentKey"))
                .orderId(json.optString("orderId"))
                .status(json.optString("status"))
                .totalAmount(json.optInt("totalAmount"))
                .approvedAt(json.optString("approvedAt"))
                .build();


        return TossPaymentResponseDto.of(json, tossPaymentResponseVo);
    }

    @Transactional
    @Override
    public TossPaymentCancelResponseDto cancelTossPayment(TossPaymentCancelRequestDto tossPaymentCancelRequestDto) {
        JSONObject json = new JSONObject(tossPaymentsApiService.cancelPayment(
                tossPaymentCancelRequestDto.getPaymentKey(),
                tossPaymentCancelRequestDto.getAmount(),
                tossPaymentCancelRequestDto.getCancelReason()
        ));

        TossPaymentCancelResponseVo tossPaymentCancelResponseVo = TossPaymentCancelResponseVo.builder()
                .paymentKey(json.optString("paymentKey"))
                .orderId(json.optString("orderId"))
                .status(json.optString("status"))
                .canceledAt(json.optString("canceledAt"))
                .canceledAmount(json.optInt("canceledAmount"))
                .totalAmount(json.optInt("totalAmount"))
                .build();

        return TossPaymentCancelResponseDto.of(json, tossPaymentCancelResponseVo);
    }



}
