package colorful.starbucks.payments.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TossPaymentRequestDto {
    private String paymentKey;
    private String orderCode;
    private Integer amount;

    @Builder
    private TossPaymentRequestDto(String paymentKey,
                                  String orderCode,
                                  Integer amount) {
        this.paymentKey = paymentKey;
        this.orderCode = orderCode;
        this.amount = amount;
    }
    public static TossPaymentRequestDto from(String paymentKey,
                                             String orderCode,
                                             Integer amount) {
        return TossPaymentRequestDto.builder()
                .paymentKey(paymentKey)
                .orderCode(orderCode)
                .amount(amount)
                .build();
}
}
