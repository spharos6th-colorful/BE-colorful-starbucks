package colorful.starbucks.order.dto.response;

import colorful.starbucks.order.vo.response.OrderCursorResponseVo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class OrderCursorResponseDto {
    private Long orderCode;
    private String orderDate;

    public static List<OrderCursorResponseDto> from(List<OrderListResponseDto> orderListResponseDtos) {
        return orderListResponseDtos.stream()
                .map(orderListResponseDto -> new OrderCursorResponseDto(
                        orderListResponseDto.getOrderCode(),
                        orderListResponseDto.getOrderDate()))
                .toList();
    }


    public OrderCursorResponseVo toVo() {
        return OrderCursorResponseVo.builder()
                .orderCode(orderCode)
                .orderDate(orderDate)
                .build();
    }
}
