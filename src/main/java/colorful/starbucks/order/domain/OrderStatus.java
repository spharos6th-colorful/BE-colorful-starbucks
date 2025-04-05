package colorful.starbucks.order.domain;

import lombok.Getter;

@Getter
public enum OrderStatus {
    PENDING("결제전"),
    PAID("결제완료"),
    CANCELLED("주문취소"),
    FAILED("결제실패")   ;

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

}
