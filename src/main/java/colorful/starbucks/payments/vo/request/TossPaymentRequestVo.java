package colorful.starbucks.payments.vo.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TossPaymentRequestVo {
    private String paymentKey;
    private String orderCode;
    private Integer amount;

    @Builder
    private TossPaymentRequestVo(String paymentKey,
                                  String orderCode,
                                  Integer amount) {
        this.paymentKey = paymentKey;
        this.orderCode = orderCode;
        this.amount = amount;
    }
}
