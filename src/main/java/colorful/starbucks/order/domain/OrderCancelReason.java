package colorful.starbucks.order.domain;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum OrderCancelReason {
    CUSTOMER_CHANGED_MIND("단순 변심"),
    ORDERED_BY_MISTAKE("잘못된 주문"),
    DELAYED_DELIVERY("배송 지연"),
    OUT_OF_STOCK("재고 없음"),
    DUPLICATE_ORDER("중복 주문"),
    WRONG_PRODUCT_ORDERED("상품 잘못 주문함"),
    PRODUCT_DEFECT("상품 하자/불량"),
    WRONG_DELIVERY("오배송"),
    PAYMENT_ISSUE("결제 문제"),
    OPTION_CHANGED("옵션 변경"),
    ADDRESS_CHANGED("배송 주소 변경"),
    OTHER("기타");

    private final String description;
    OrderCancelReason(String description) {
        this.description = description;
    }

    public static OrderCancelReason getResponse(String description) {
        return Arrays.stream(values())
                .filter(orderCancelReason -> orderCancelReason.getDescription().equals(description))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 취소 사유입니다.: " + description));
    }
}


