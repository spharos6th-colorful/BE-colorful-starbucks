package colorful.starbucks.payments.vo.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TossPaymentRequestVo {
    private String paymentKey;
    private String orderId;
    private Integer amount;
    private String memberUuid;

    @Builder
    private TossPaymentRequestVo(String paymentKey,
                                  String orderId,
                                 Integer amount,
                                 String memberUuid) {
        this.paymentKey = paymentKey;
        this.orderId = orderId;
        this.amount = amount;
        this.memberUuid = memberUuid;
    }
}
