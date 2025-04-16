package colorful.starbucks.order.dto.response;

import colorful.starbucks.order.domain.Order;
import colorful.starbucks.order.vo.response.OrderCursorResponseVo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class OrderCursorResponseDto {
    private Long orderCode;
    private LocalDateTime createdAt;
    private Integer totalAmount;
    private Integer discountAmount;

    public OrderCursorResponseDto(Long orderCode, LocalDateTime createdAt, Integer totalAmount, Integer discountAmount) {
        this.orderCode = orderCode;
        this.createdAt = createdAt;
        this.totalAmount = totalAmount;
        this.discountAmount = discountAmount;
    }


    public static OrderCursorResponseDto from(Order order) {
        return new OrderCursorResponseDto(
                order.getOrderCode(),
                order.getCreatedAt(),
                order.getTotalAmount(),
                order.getDiscountAmount()
        );

    }


    public OrderCursorResponseVo toVo() {
        return OrderCursorResponseVo.builder()
                .orderCode(orderCode)
                .createdAt(createdAt)
                .totalAmount(totalAmount)
                .discountAmount(discountAmount)
                .build();
    }
}
