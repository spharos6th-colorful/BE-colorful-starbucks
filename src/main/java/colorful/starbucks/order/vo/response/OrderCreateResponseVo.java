package colorful.starbucks.order.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderCreateResponseVo {

    private Long orderCode;
    private Integer totalAmount;

    @Builder
    private OrderCreateResponseVo(Long orderCode,
                                  Integer totalAmount) {
        this.totalAmount = totalAmount;
        this.orderCode = orderCode;
    }

}
