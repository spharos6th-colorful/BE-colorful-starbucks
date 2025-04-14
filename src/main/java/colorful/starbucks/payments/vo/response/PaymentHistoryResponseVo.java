package colorful.starbucks.payments.vo.response;

import colorful.starbucks.payments.domain.PaymentStatus;
import colorful.starbucks.payments.domain.PaymentsType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PaymentHistoryResponseVo {
    private String paymentKey;
    private String paymentsNumber;
    private String orderCode;
    private PaymentsType paymentsType;
    private PaymentStatus paymentStatus;
    private Integer totalPrice;
    private String approvedAt;
    private String cancelReason;
    private String cancelAt;

    @Builder
    private PaymentHistoryResponseVo(String paymentKey,
                                      String paymentsNumber,
                                      String orderCode,
                                      PaymentsType paymentsType,
                                      PaymentStatus paymentStatus,
                                      Integer totalPrice,
                                      String approvedAt,
                                      String cancelReason,
                                      String cancelAt) {
        this.paymentKey = paymentKey;
        this.paymentsNumber = paymentsNumber;
        this.orderCode = orderCode;
        this.paymentsType = paymentsType;
        this.paymentStatus = paymentStatus;
        this.totalPrice = totalPrice;
        this.approvedAt = approvedAt;
        this.cancelReason = cancelReason;
        this.cancelAt = cancelAt;
    }
}
