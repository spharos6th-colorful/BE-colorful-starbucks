package colorful.starbucks.payments.dto.response;

import colorful.starbucks.payments.domain.PaymentHistory;
import colorful.starbucks.payments.domain.PaymentsStatus;
import colorful.starbucks.payments.domain.PaymentsType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PaymentHistoryResponseDto {
    private String paymentKey;
    private String paymentsNumber;
    private String orderCode;
    private PaymentsType paymentsType;
    private PaymentsStatus paymentsStatus;
    private Integer totalPrice;
    private String approvedAt;
    private String cancelReason;
    private String canceledAt;

    @Builder
    private PaymentHistoryResponseDto(String paymentKey,
                                      String paymentsNumber,
                                      String orderCode,
                                      PaymentsType paymentsType,
                                      PaymentsStatus paymentsStatus,
                                      Integer totalPrice,
                                      String approvedAt,
                                      String cancelReason,
                                      String canceledAt) {
        this.paymentKey = paymentKey;
        this.paymentsNumber = paymentsNumber;
        this.orderCode = orderCode;
        this.paymentsType = paymentsType;
        this.paymentsStatus = paymentsStatus;
        this.totalPrice = totalPrice;
        this.approvedAt = approvedAt;
        this.cancelReason = cancelReason;
        this.canceledAt = canceledAt;
    }
    public static PaymentHistoryResponseDto fromEntity(PaymentHistory paymentHistory) {
        return PaymentHistoryResponseDto.builder()
                .paymentKey(paymentHistory.getPaymentKey())
                .paymentsNumber(paymentHistory.getPaymentsNumber())
                .orderCode(paymentHistory.getOrderCode())
                .paymentsType(paymentHistory.getPaymentsType())
                .paymentsStatus(paymentHistory.getPaymentsStatus())
                .totalPrice(paymentHistory.getTotalPrice())
                .approvedAt(paymentHistory.getApprovedAt())
                .cancelReason(paymentHistory.getCancelReason())
                .canceledAt(paymentHistory.getCanceledAt())
                .build();
    }
}
