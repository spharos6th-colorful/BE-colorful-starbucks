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
    private String status;
    private Integer totalAmount;
    private String canceledAt;
    private int canceledAmount;

    @Builder
    private TossPaymentCancelResponseDto(String paymentKey,
                                         String orderId,
                                         String status,
                                         Integer totalAmount,
                                         String canceledAt,
                                         int canceledAmount) {
        this.paymentKey = paymentKey;
        this.orderId = orderId;
        this.status = status;
        this.totalAmount = totalAmount;
        this.canceledAt = canceledAt;
        this.canceledAmount = canceledAmount;
    }

    public static TossPaymentCancelResponseDto fromJson(String json) {
        JSONObject jsonObject = new JSONObject(json);
        return TossPaymentCancelResponseDto.builder()
                .paymentKey(jsonObject.getString("paymentKey"))
                .orderId(jsonObject.getString("orderId"))
                .status(jsonObject.getString("status"))
                .totalAmount(jsonObject.getInt("totalAmount"))
                .canceledAt(jsonObject.getString("canceledAt"))
                .canceledAmount(jsonObject.getInt("canceledAmount"))
                .build();
    }

    public TossPaymentCancelResponseVo toVo() {
        return TossPaymentCancelResponseVo.builder()
                .paymentKey(this.paymentKey)
                .orderId(this.orderId)
                .status(this.status)
                .totalAmount(this.totalAmount)
                .canceledAt(this.canceledAt)
                .canceledAmount(this.canceledAmount)
                .build();
    }
}