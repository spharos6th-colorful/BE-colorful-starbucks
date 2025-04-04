package colorful.starbucks.order.dto.out;

import colorful.starbucks.order.vo.out.OrderCreateResponseVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderCreateResponseDto {

    private String orderCode;

    @Builder
    private OrderCreateResponseDto(String orderCode) {
        this.orderCode = orderCode;
    }

    public OrderCreateResponseVo toVo() {
        return OrderCreateResponseVo.builder()
                .orderCode(this.orderCode)
                .build();
    }
}
