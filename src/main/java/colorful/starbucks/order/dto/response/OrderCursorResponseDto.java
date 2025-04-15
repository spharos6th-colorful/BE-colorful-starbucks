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
    private Long id;
    private LocalDateTime createdAt;
    private Integer totalAmount;
    private Integer discountAmount;

    public OrderCursorResponseDto(Long id, LocalDateTime createdAt, Integer totalAmount, Integer discountAmount) {
        this.id = id;
        this.createdAt = createdAt;
        this.totalAmount = totalAmount;
        this.discountAmount = discountAmount;
    }


    public static List<OrderCursorResponseDto> from(List<OrderResponseDto> orderResponseDtos) {
        return orderResponseDtos.stream()
                .map(orderResponseDto -> new OrderCursorResponseDto(
                        orderResponseDto.getId(),
                        orderResponseDto.getCreatedAt(),
                        orderResponseDto.getTotalAmount(),
                        orderResponseDto.getDiscountAmount()))
                .toList();
    }


    public OrderCursorResponseVo toVo() {
        return OrderCursorResponseVo.builder()
                .id(id)
                .createdAt(createdAt)
                .totalAmount(totalAmount)
                .discountAmount(discountAmount)
                .build();
    }
}
