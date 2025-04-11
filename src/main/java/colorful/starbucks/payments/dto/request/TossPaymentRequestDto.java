package colorful.starbucks.payments.dto.request;

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
}
