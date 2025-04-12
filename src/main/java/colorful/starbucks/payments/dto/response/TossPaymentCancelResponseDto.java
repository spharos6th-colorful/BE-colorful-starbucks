package colorful.starbucks.payments.dto.response;

import colorful.starbucks.payments.vo.response.TossPaymentCancelResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

@Getter
@NoArgsConstructor
public class TossPaymentCancelResponseDto {
    private String paymentKey;
    private String orderId;
    private String orderName;
    private String status;
    private String canceledAt;
    private int canceledAmount;
    private int totalAmount;
    private String method;
    private String receiptUrl;

    @Builder
    private TossPaymentCancelResponseDto(String paymentKey,
                                        String orderId,
                                        String orderName,
                                        String status,
                                        String canceledAt,
                                        int canceledAmount,
                                        int totalAmount,
                                        String method,
                                        String receiptUrl) {
        this.paymentKey = paymentKey;
        this.orderId = orderId;
        this.orderName = orderName;
        this.status = status;
        this.canceledAt = canceledAt;
        this.canceledAmount = canceledAmount;
        this.totalAmount = totalAmount;
        this.method = method;
        this.receiptUrl = receiptUrl;
    }
    public static TossPaymentCancelResponseDto of(JSONObject json, TossPaymentCancelResponseVo vo) {
        return TossPaymentCancelResponseDto.builder()
                .paymentKey(vo.getPaymentKey())
                .orderId(vo.getOrderId())
                .orderName(json.optString("orderName"))
                .status(vo.getStatus())
                .canceledAt(vo.getCanceledAt())
                .canceledAmount(vo.getCanceledAmount())
                .totalAmount(vo.getTotalAmount())
                .method(json.optString("method"))
                .receiptUrl(json.optString("receiptUrl"))
                .build();
    }
}
