package colorful.starbucks.payments.vo.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TossPaymentCancelRequestVo {
    private String paymentKey;
    private String orderId;
    private int amount;
    private String cancelReason;

    @Builder
    private TossPaymentCancelRequestVo(String paymentKey, String orderId, int amount, String cancelReason) {
        this.paymentKey = paymentKey;
        this.orderId = orderId;
        this.amount = amount;
        this.cancelReason = cancelReason;
    }
}



