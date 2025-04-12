package colorful.starbucks.order.dto.response;

import colorful.starbucks.order.vo.response.OrderCreateResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderCreateResponseDto {

    private String orderCode;

    @Builder
    private OrderCreateResponseDto(String orderCode) {
        this.orderCode = orderCode;
    }

    public static OrderCreateResponseDto from(Long orderCode) {
        return new OrderCreateResponseDto(String.valueOf(orderCode));
    }

    public OrderCreateResponseVo toVo() {
        return OrderCreateResponseVo.builder()
                .orderCode(this.orderCode)
                .build();
    }
}
