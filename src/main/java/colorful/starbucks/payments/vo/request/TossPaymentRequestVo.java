package colorful.starbucks.payments.vo.request;

import lombok.Getter;

@Getter
public class TossPaymentRequestVo {

    private String paymentKey;
    private String orderId;
    private Integer amount;
}
