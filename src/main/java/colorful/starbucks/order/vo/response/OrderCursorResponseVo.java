package colorful.starbucks.order.vo.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderCursorResponseVo {

    private Long orderCode;
    private String orderDate;

    @Builder
    private OrderCursorResponseVo(Long orderCode, String orderDate) {
        this.orderCode = orderCode;
        this.orderDate = orderDate;
    }
}



