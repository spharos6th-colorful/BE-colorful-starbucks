package colorful.starbucks.order.dto.response;

import colorful.starbucks.order.vo.response.OrderCursorResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class OrderCursorResponseDto {
    private Long orderCode;
    private LocalDateTime createdAt;
    private int totalAmount;
    private int discountAmount;


    @Builder
    public OrderCursorResponseDto(LocalDateTime createdAt,
                                  Long orderCode,
                                  int totalAmount,
                                  int discountAmount) {
        this.createdAt = createdAt;
        this.orderCode = orderCode;
        this.totalAmount = totalAmount;
        this.discountAmount = discountAmount;
    }


    public static List<OrderCursorResponseDto> from(List<OrderResponseDto> orderResponseDtos) {
        return orderResponseDtos.stream()
                .map(orderResponseDto -> new OrderCursorResponseDto(
                        orderResponseDto.getCreatedAt(),
                        orderResponseDto.getOrderCode(),
                        orderResponseDto.getTotalAmount(),
                        orderResponseDto.getDiscountAmount()))
                .toList();
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
