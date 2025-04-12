package colorful.starbucks.payments.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TossPaymentCancelRequestDto {
    private String paymentKey;
    private String orderId;
    private int amount;
    private String cancelReason;

    @Builder
    private TossPaymentCancelRequestDto(String paymentKey,
                                         String orderId,
                                         int amount,
                                        String cancelReason) {
        this.paymentKey = paymentKey;
        this.orderId = orderId;
        this.amount = amount;
        this.cancelReason = cancelReason;
    }
    public static TossPaymentCancelRequestDto from(String paymentKey,
                                                    String orderId,
                                                    int amount,
                                                   String cancelReason) {
        return TossPaymentCancelRequestDto.builder()
                .paymentKey(paymentKey)
                .orderId(orderId)
                .amount(amount)
                .cancelReason(cancelReason)
                .build();
    }
}
