package colorful.starbucks.payments.vo.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TossPaymentCancelRequestVo {
    private String paymentKey;
    private String orderId;
    private Integer amount;
    private String cancelReason;
    private String memberUuid;

    @Builder
    private TossPaymentCancelRequestVo(String paymentKey,
                                       String orderId,
                                       Integer amount,
                                       String cancelReason,
                                       String memberUuid) {
        this.paymentKey = paymentKey;
        this.orderId = orderId;
        this.amount = amount;
        this.cancelReason = cancelReason;
        this.memberUuid = memberUuid;
    }
}



