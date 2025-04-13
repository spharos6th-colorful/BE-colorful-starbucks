package colorful.starbucks.payments.application;

import colorful.starbucks.common.exception.BaseException;
import colorful.starbucks.common.response.ResponseStatus;
import colorful.starbucks.payments.dto.request.TossPaymentCancelRequestDto;
import colorful.starbucks.payments.dto.request.TossPaymentRequestDto;
import colorful.starbucks.payments.dto.response.PaymentHistoryResponseDto;
import colorful.starbucks.payments.dto.response.TossPaymentCancelResponseDto;
import colorful.starbucks.payments.dto.response.TossPaymentResponseDto;
import colorful.starbucks.payments.infrastructure.PaymentHistoryRepository;
import colorful.starbucks.payments.vo.response.TossPaymentCancelResponseVo;
import colorful.starbucks.payments.vo.response.TossPaymentResponseVo;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PaymentsServiceImpl implements PaymentsService {

    private final TossPaymentsApiService tossPaymentsApiService;
    private final PaymentHistoryRepository paymentHistoryRepository;

    @Transactional
    @Override
    public TossPaymentResponseDto approveTossPayment(TossPaymentRequestDto tossPaymentRequestDto, String memberUuid) {
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



        paymentHistoryRepository.save(
                tossPaymentRequestDto.toEntity(
                        memberUuid,
                        json.optString("approvedAt"),
                        json.optString("method")
                )
        );


        return TossPaymentResponseDto.of(json, tossPaymentResponseVo);
    }

    @Transactional
    @Override
    public TossPaymentCancelResponseDto cancelTossPayment(TossPaymentCancelRequestDto tossPaymentCancelRequestDto, String memberUuid) {
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

        cancelUpdatePaymentHistory(
                tossPaymentCancelRequestDto.getCancelReason(),
                tossPaymentCancelRequestDto.getPaymentKey(),
                json.optString("canceledAt")
        );

        return TossPaymentCancelResponseDto.of(json, tossPaymentCancelResponseVo);
    }

    @Override
    public List<PaymentHistoryResponseDto> getPaymentHistory(String memberUuid) {
        List<PaymentHistoryResponseDto> paymentHistoryResponseDtos = paymentHistoryRepository.findByMemberUuidOrderByCreatedAtDesc(memberUuid)
                .stream()
                .map(PaymentHistoryResponseDto::fromEntity)
                .toList();

        if (paymentHistoryResponseDtos.isEmpty()) {
            throw new BaseException(ResponseStatus.INVALID_PAYMENTS_STATUS,
                    "결제 내역이 존재하지 않습니다.");
        }

        return paymentHistoryResponseDtos;
    }

    @Override
    public List<PaymentHistoryResponseDto> getPaymentHistoryByOrderCode(String orderCode, String memberUuid) {
        List<PaymentHistoryResponseDto> paymentHistoryResponseDtos = paymentHistoryRepository.findByOrderCodeAndMemberUuid(orderCode, memberUuid)
                .stream()
                .map(PaymentHistoryResponseDto::fromEntity)
                .toList();

        if (paymentHistoryResponseDtos.isEmpty()) {
            throw new BaseException(ResponseStatus.INVALID_PAYMENTS_STATUS,
                    "결제 내역이 존재하지 않습니다.");
        }

        return paymentHistoryResponseDtos;
    }


    private void cancelUpdatePaymentHistory(String cancelReason, String paymentKey, String canceledAt) {
        paymentHistoryRepository.findByPaymentsNumber(paymentKey)
                .orElseThrow(() -> new BaseException(ResponseStatus.INVALID_PAYMENTS_STATUS,
                        "결제 내역이 존재하지 않습니다."));
        paymentHistoryRepository.updatePaymentHistory(
                paymentKey,
                canceledAt,
                cancelReason
        );
    }



}
