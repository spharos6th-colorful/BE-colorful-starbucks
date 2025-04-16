package colorful.starbucks.payments.dto.request;

import colorful.starbucks.payments.domain.PaymentHistory;
import colorful.starbucks.payments.domain.PaymentStatus;
import colorful.starbucks.payments.domain.PaymentsType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TossPaymentRequestDto {
    private String paymentKey;
    private String orderId;
    private Integer amount;
    private String memberUuid;

    @Builder
    private TossPaymentRequestDto(String paymentKey,
                                  String orderId,
                                  Integer amount,
                                  String memberUuid) {
        this.paymentKey = paymentKey;
        this.orderId = orderId;
        this.amount = amount;
        this.memberUuid = memberUuid;
    }
    public static TossPaymentRequestDto from(String paymentKey,
                                             String orderId,
                                             Integer amount,
                                             String memberUuid) {
        return TossPaymentRequestDto.builder()
                .paymentKey(paymentKey)
                .orderId(orderId)
                .amount(amount)
                .memberUuid(memberUuid)
                .build();
}
    public static TossPaymentRequestDto of(TossPaymentRequestDto tossPaymentRequestDto, String memberUuid) {
        return TossPaymentRequestDto.builder()
                .paymentKey(tossPaymentRequestDto.getPaymentKey())
                .orderId(tossPaymentRequestDto.getOrderId())
                .amount(tossPaymentRequestDto.getAmount())
                .memberUuid(memberUuid)
                .build();
    }

    public PaymentHistory toEntity(String approvedAt, String method) {
        return PaymentHistory.builder()
                .totalPrice(amount)
                .paymentsNumber(paymentKey)
                .orderCode(Long.valueOf(orderId))
                .memberUuid(memberUuid)
                .paymentsType(PaymentsType.valueOf(method.toUpperCase()))
                .paymentStatus(PaymentStatus.APPROVED)
                .approvedAt(approvedAt)
                .paymentKey(paymentKey)
                .build();
    }


}
