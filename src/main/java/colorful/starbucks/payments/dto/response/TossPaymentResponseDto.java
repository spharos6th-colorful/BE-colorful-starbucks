package colorful.starbucks.payments.dto.response;

import colorful.starbucks.payments.vo.response.TossPaymentResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

@Getter
@NoArgsConstructor
public class TossPaymentResponseDto {
    private String paymentKey;
    private String orderId;
    private String orderName;
    private String status;
    private String approvedAt;
    private int totalAmount;
    private String method;
    private String cardCompany;
    private String cardNumber;
    private Integer installmentMonths;
    private String receiptUrl;

    @Builder
    private TossPaymentResponseDto(String paymentKey,
                                    String orderId,
                                    String orderName,
                                    String status,
                                    String approvedAt,
                                    int totalAmount,
                                    String method,
                                    String cardCompany,
                                    String cardNumber,
                                    Integer installmentMonths,
                                    String receiptUrl) {
        this.paymentKey = paymentKey;
        this.orderId = orderId;
        this.orderName = orderName;
        this.status = status;
        this.approvedAt = approvedAt;
        this.totalAmount = totalAmount;
        this.method = method;
        this.cardCompany = cardCompany;
        this.cardNumber = cardNumber;
        this.installmentMonths = installmentMonths;
        this.receiptUrl = receiptUrl;
    }
    public static TossPaymentResponseDto of(JSONObject json, TossPaymentResponseVo vo) {
        JSONObject card = json.optJSONObject("card");
        JSONObject receipt = json.optJSONObject("receipt");

        return TossPaymentResponseDto.builder()
                .paymentKey(vo.getPaymentKey())
                .orderId(vo.getOrderId())
                .orderName(json.optString("orderName"))
                .status(vo.getStatus())
                .approvedAt(vo.getApprovedAt())
                .totalAmount(vo.getTotalAmount())
                .method(json.optString("method"))
                .cardCompany(card != null ? card.optString("company") : null)
                .cardNumber(card != null ? card.optString("number") : null)
                .installmentMonths(card != null ? card.optInt("installmentPlanMonths") : null)
                .receiptUrl(receipt != null ? receipt.optString("url") : null)
                .build();
    }



}
