package colorful.starbucks.order.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderCreateResponseVo {

    private Long orderCode;

    @Builder
    private OrderCreateResponseVo(Long orderCode) {
        this.orderCode = orderCode;
    }

}
