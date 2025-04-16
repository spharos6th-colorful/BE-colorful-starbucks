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
    private Integer totalAmount;
    private String canceledAt;
    private Integer canceledAmount;

    @Builder
    private TossPaymentCancelResponseVo(String paymentKey,
                                        String orderId,
                                        String status,
                                        Integer totalAmount,
                                        String canceledAt,
                                        Integer canceledAmount) {
        this.paymentKey = paymentKey;
        this.orderId = orderId;
        this.status = status;
        this.totalAmount = totalAmount;
        this.canceledAt = canceledAt;
        this.canceledAmount = canceledAmount;
    }
}
