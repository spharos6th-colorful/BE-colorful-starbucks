package colorful.starbucks.payments.vo.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TossPaymentRequestVo {
    private String paymentKey;
    private String orderId;
    private int amount;

    @Builder
    private TossPaymentRequestVo(String paymentKey,
                                  String orderId,
                                 int amount) {
        this.paymentKey = paymentKey;
        this.orderId = orderId;
        this.amount = amount;
    }
}
