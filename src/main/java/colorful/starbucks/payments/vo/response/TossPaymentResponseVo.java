package colorful.starbucks.payments.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class TossPaymentResponseVo {
    private String paymentKey;
    private String orderId;
    private String status;
    private int totalAmount;
    private String approvedAt;

    @Builder
    private TossPaymentResponseVo(String paymentKey,
                                   String orderId,
                                   String status,
                                   int totalAmount,
                                   String approvedAt) {
        this.paymentKey = paymentKey;
        this.orderId = orderId;
        this.status = status;
        this.totalAmount = totalAmount;
        this.approvedAt = approvedAt;
    }

}
