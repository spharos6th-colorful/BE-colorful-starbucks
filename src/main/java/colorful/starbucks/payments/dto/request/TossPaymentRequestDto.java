package colorful.starbucks.payments.dto.request;

import colorful.starbucks.payments.domain.PaymentHistory;
import colorful.starbucks.payments.domain.PaymentsStatus;
import colorful.starbucks.payments.domain.PaymentsType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TossPaymentRequestDto {
    private String paymentKey;
    private String orderId;
    private int amount;

    @Builder
    private TossPaymentRequestDto(String paymentKey,
                                  String orderId,
                                  int amount) {
        this.paymentKey = paymentKey;
        this.orderId = orderId;
        this.amount = amount;
    }
    public static TossPaymentRequestDto from(String paymentKey,
                                             String orderId,
                                             int amount) {
        return TossPaymentRequestDto.builder()
                .paymentKey(paymentKey)
                .orderId(orderId)
                .amount(amount)
                .build();
}
    public PaymentHistory toEntity(String memberUuid, String approvedAt, String method) {
        return PaymentHistory.builder()
                .totalPrice(amount)
                .paymentsNumber(paymentKey)
                .orderCode(orderId)
                .memberUuid(memberUuid)
                .paymentsType(PaymentsType.valueOf(method.toUpperCase()))
                .paymentsStatus(PaymentsStatus.APPROVED)
                .approvedAt(approvedAt)
                .build();
    }
}
