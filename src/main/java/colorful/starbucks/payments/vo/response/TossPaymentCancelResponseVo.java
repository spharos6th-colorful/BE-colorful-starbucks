package colorful.starbucks.payments.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TossPaymentCancelResponseVo {
    private String paymentKey;
    private String orderId;
    private String status;
    private int totalAmount;
    private String canceledAt;
    private int canceledAmount;

    @Builder
    private TossPaymentCancelResponseVo(String paymentKey,
                                        String orderId,
                                        String status,
                                        int totalAmount,
                                        String canceledAt,
                                        int canceledAmount) {
        this.paymentKey = paymentKey;
        this.orderId = orderId;
        this.status = status;
        this.totalAmount = totalAmount;
        this.canceledAt = canceledAt;
        this.canceledAmount = canceledAmount;
    }
}
