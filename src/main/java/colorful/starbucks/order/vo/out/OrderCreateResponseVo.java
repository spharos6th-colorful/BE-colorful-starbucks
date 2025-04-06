package colorful.starbucks.order.vo.out;

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
