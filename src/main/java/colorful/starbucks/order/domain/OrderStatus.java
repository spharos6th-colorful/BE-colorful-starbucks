package colorful.starbucks.order.domain;

import colorful.starbucks.common.exception.BaseException;
import colorful.starbucks.common.response.ResponseStatus;
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

    public static OrderStatus fromDescription(String description) {
        for (OrderStatus status : values()) {
            if (status.getDescription().equals(description)) {
                return status;
            }
        }
        throw new BaseException(ResponseStatus.INVALID_ORDER_STATUS, "존재하지 않는 주문 상태입니다: " + description);
    }
}



