package colorful.starbucks.order.dto.response;

import colorful.starbucks.order.vo.response.OrderCursorResponseVo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class OrderCursorResponseDto {
    private Long orderCode;
    private LocalDateTime createdAt;
    private Integer totalAmount;
    private Integer discountAmount;


    @Builder
    public OrderCursorResponseDto(LocalDateTime createdAt,
                                  Long orderCode,
                                  Integer totalAmount,
                                  Integer discountAmount) {
        this.createdAt = createdAt;
        this.orderCode = orderCode;
        this.totalAmount = totalAmount;
        this.discountAmount = discountAmount;
    }


    public static List<OrderCursorResponseDto> from(List<OrderListResponseDto> orderListResponseDtos) {
        return orderListResponseDtos.stream()
                .map(orderListResponseDto -> new OrderCursorResponseDto(
                        orderListResponseDto.getCreatedAt(),
                        orderListResponseDto.getOrderCode(),
                        orderListResponseDto.getTotalAmount(),
                        orderListResponseDto.getDiscountAmount()))
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
