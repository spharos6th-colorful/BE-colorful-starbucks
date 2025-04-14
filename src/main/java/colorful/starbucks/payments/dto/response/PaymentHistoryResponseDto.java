package colorful.starbucks.payments.dto.response;

import colorful.starbucks.payments.domain.PaymentHistory;
import colorful.starbucks.payments.domain.PaymentStatus;
import colorful.starbucks.payments.domain.PaymentsType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PaymentHistoryResponseDto {
    private String paymentKey;
    private String paymentsNumber;
    private Long orderCode;
    private PaymentsType paymentsType;
    private PaymentStatus paymentStatus;
    private Integer totalPrice;
    private String approvedAt;
    private String cancelReason;
    private String canceledAt;

    @Builder
    private PaymentHistoryResponseDto(String paymentKey,
                                      String paymentsNumber,
                                      Long orderCode,
                                      PaymentsType paymentsType,
                                      PaymentStatus paymentStatus,
                                      Integer totalPrice,
                                      String approvedAt,
                                      String cancelReason,
                                      String canceledAt) {
        this.paymentKey = paymentKey;
        this.paymentsNumber = paymentsNumber;
        this.orderCode = orderCode;
        this.paymentsType = paymentsType;
        this.paymentStatus = paymentStatus;
        this.totalPrice = totalPrice;
        this.approvedAt = approvedAt;
        this.cancelReason = cancelReason;
        this.canceledAt = canceledAt;
    }
    public static PaymentHistoryResponseDto from(PaymentHistory paymentHistory) {
        return PaymentHistoryResponseDto.builder()
                .paymentKey(paymentHistory.getPaymentKey())
                .paymentsNumber(paymentHistory.getPaymentsNumber())
                .orderCode(paymentHistory.getOrderCode())
                .paymentsType(paymentHistory.getPaymentsType())
                .paymentStatus(paymentHistory.getPaymentStatus())
                .totalPrice(paymentHistory.getTotalPrice())
                .approvedAt(paymentHistory.getApprovedAt())
                .cancelReason(paymentHistory.getCancelReason())
                .canceledAt(paymentHistory.getCanceledAt())
                .build();
    }
    public static PaymentHistoryResponseDto of(PaymentHistory paymentHistory,Long orderCode, String memberUuid) {
        return PaymentHistoryResponseDto.builder()
                .paymentKey(paymentHistory.getPaymentKey())
                .paymentsNumber(paymentHistory.getPaymentsNumber())
                .orderCode(paymentHistory.getOrderCode())
                .paymentsType(paymentHistory.getPaymentsType())
                .paymentStatus(paymentHistory.getPaymentStatus())
                .totalPrice(paymentHistory.getTotalPrice())
                .approvedAt(paymentHistory.getApprovedAt())
                .cancelReason(paymentHistory.getCancelReason())
                .canceledAt(paymentHistory.getCanceledAt())
                .build();
    }
}
