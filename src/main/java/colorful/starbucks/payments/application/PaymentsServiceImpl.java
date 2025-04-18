package colorful.starbucks.payments.application;

import colorful.starbucks.cart.application.CartService;
import colorful.starbucks.common.exception.BaseException;
import colorful.starbucks.common.response.ResponseStatus;
import colorful.starbucks.order.application.OrderRedisService;
import colorful.starbucks.order.application.OrderService;
import colorful.starbucks.order.dto.PreOrderDto;
import colorful.starbucks.order.dto.request.OrderCreateRequestDto;
import colorful.starbucks.order.dto.request.OrderDetailCreateRequestDto;
import colorful.starbucks.payments.domain.PaymentHistory;
import colorful.starbucks.payments.domain.PaymentStatus;
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
    private final OrderRedisService orderRedisService;
    private final OrderService orderService;
    private final CartService cartService;

    @Transactional
    @Override
    public TossPaymentResponseDto approveTossPayment(
            TossPaymentRequestDto tossPaymentRequestDto,
            OrderCreateRequestDto orderCreateRequestDto
    ) {
        Long orderCode = Long.parseLong(tossPaymentRequestDto.getOrderId());
        Integer paidAmount = tossPaymentRequestDto.getAmount();


        orderRedisService.validateOrderForPayment(orderCode, paidAmount);


        JSONObject json = new JSONObject(tossPaymentsApiService.approvePayment(
                tossPaymentRequestDto.getPaymentKey(),
                tossPaymentRequestDto.getOrderId(),
                tossPaymentRequestDto.getAmount()
        ));

        PreOrderDto preOrderDto = orderRedisService.getOrder(orderCode);
        if (preOrderDto == null) {
            throw new BaseException(ResponseStatus.NO_EXIST_ORDER);
        }


        orderService.createOrder(orderCreateRequestDto);
        List<Long> cartIds = orderCreateRequestDto.getOrderDetails().stream().map(OrderDetailCreateRequestDto::getProductDetailCode)
                .toList();
        cartService.removeCartByMemberAndProductDetailCodes(
                orderCreateRequestDto.getMemberUuid(),
                cartIds
        );


        paymentHistoryRepository.save(
                tossPaymentRequestDto.toEntity(
                        json.optString("approvedAt"),
                        json.optString("method")
                )
        );


        orderRedisService.deleteOrder(orderCode);


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


        TossPaymentCancelResponseDto tossPaymentCancelResponseDto = TossPaymentCancelResponseDto.fromJson(json.toString());
        TossPaymentCancelResponseVo tossPaymentCancelResponseVo = tossPaymentCancelResponseDto.toVo();

        createCanceledPaymentHistory(tossPaymentCancelRequestDto, tossPaymentCancelResponseVo, tossPaymentCancelRequestDto.getMemberUuid());

        return tossPaymentCancelResponseDto;
    }

    @Override
    public List<PaymentHistoryResponseDto> getPaymentHistory(String memberUuid) {
        return paymentHistoryRepository.findByMemberUuidOrderByCreatedAtDesc(memberUuid)
                .stream()
                .map(PaymentHistoryResponseDto::from)
                .toList();
    }

    @Override
    public List<PaymentHistoryResponseDto> getPaymentHistoryByOrderCode(Long orderCode, String memberUuid) {
        return paymentHistoryRepository.findByOrderCodeAndMemberUuid(orderCode, memberUuid)
                .stream()
                .map(PaymentHistoryResponseDto::from)
                .toList();
    }


    private void createCanceledPaymentHistory(
            TossPaymentCancelRequestDto tossPaymentCancelRequestDto,
            TossPaymentCancelResponseVo tossPaymentCancelResponseVo,
            String memberUuid
    ) {

        PaymentHistory original = paymentHistoryRepository.findByPaymentsNumber(tossPaymentCancelRequestDto.getPaymentKey())
                .orElseThrow(() -> new BaseException(ResponseStatus.INVALID_PAYMENTS_STATUS, "결제 내역이 존재하지 않습니다."));

        PaymentHistory canceledHistory = PaymentHistory.builder()
                .id(original.getId())
                .paymentKey(tossPaymentCancelRequestDto.getPaymentKey())
                .paymentsNumber(original.getPaymentsNumber())
                .orderCode(original.getOrderCode())
                .memberUuid(memberUuid)
                .paymentsType(original.getPaymentsType())
                .paymentStatus(PaymentStatus.CANCELLED)
                .cancelReason(tossPaymentCancelRequestDto.getCancelReason())
                .approvedAt(original.getApprovedAt())
                .canceledAt(tossPaymentCancelResponseVo.getCanceledAt())
                .totalPrice(original.getTotalPrice())
                .build();

        paymentHistoryRepository.save(canceledHistory);
    }


}
