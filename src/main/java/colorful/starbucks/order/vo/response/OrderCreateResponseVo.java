package colorful.starbucks.order.vo.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderCreateResponseVo {

    private String orderCode;

    @Builder
    private OrderCreateResponseVo(String orderCode) {
        this.orderCode = orderCode;
    }

}
