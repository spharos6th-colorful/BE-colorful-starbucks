package colorful.starbucks.payments.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TossPaymentCancelRequestDto {
    private String paymentKey;
    private String orderId;
    private Integer amount;
    private String cancelReason;
    private String memberUuid;

    @Builder
    private TossPaymentCancelRequestDto(String paymentKey,
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
    public static TossPaymentCancelRequestDto from(String paymentKey,
                                                    String orderId,
                                                    Integer amount,
                                                   String cancelReason,
                                                   String memberUuid) {
        return TossPaymentCancelRequestDto.builder()
                .paymentKey(paymentKey)
                .orderId(orderId)
                .amount(amount)
                .cancelReason(cancelReason)
                .memberUuid(memberUuid)
                .build();
    }

    public static TossPaymentCancelRequestDto of(TossPaymentCancelRequestDto tossPaymentCancelRequestDto, String memberUuid) {
        return TossPaymentCancelRequestDto.builder()
                .paymentKey(tossPaymentCancelRequestDto.getPaymentKey())
                .orderId(tossPaymentCancelRequestDto.getOrderId())
                .amount(tossPaymentCancelRequestDto.getAmount())
                .memberUuid(memberUuid)
                .build();
    }
}
