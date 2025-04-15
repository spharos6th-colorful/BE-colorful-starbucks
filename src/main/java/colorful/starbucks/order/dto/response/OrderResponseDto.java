package colorful.starbucks.order.dto.response;


import colorful.starbucks.order.dto.OrderAddressDto;
import colorful.starbucks.order.vo.response.OrderResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class OrderResponseDto {

    private Long id;
    private LocalDateTime createdAt;
    private Long orderCode;
    private Integer totalAmount;
    private Integer discountAmount;
    private List<OrderDetailResponseDto> orderDetails;
    private OrderAddressDto orderAddress;

    @Builder
    private OrderResponseDto(Long id,
                             LocalDateTime createdAt,
                             Long orderCode,
                             Integer totalAmount,
                             Integer discountAmount,
                             List<OrderDetailResponseDto> orderDetails,
                             OrderAddressDto orderAddress) {
        this.id = id;
        this.createdAt = createdAt;
        this.orderCode = orderCode;
        this.totalAmount = totalAmount;
        this.discountAmount = discountAmount;
        this.orderDetails = orderDetails;
        this.orderAddress = orderAddress;
    }


    public OrderResponseVo toVo() {
        return OrderResponseVo.builder()
                .id(id)
                .createdAt(createdAt)
                .orderCode(orderCode)
                .totalAmount(totalAmount)
                .discountAmount(discountAmount)
                .orderDetails(orderDetails.stream().map(OrderDetailResponseDto::toVo).toList())
                .OrderAddress(orderAddress.toVo())
                .build();
    }

    public static OrderResponseDto of(Long id,
                                      LocalDateTime createdAt,
                                      Long orderCode,
                                      int totalAmount,
                                      int discountAmount,
                                      List<OrderDetailResponseDto> orderDetails,
                                      OrderAddressDto orderAddress) {
        return OrderResponseDto.builder()
                .id(id)
                .createdAt(createdAt)
                .orderCode(orderCode)
                .totalAmount(totalAmount)
                .discountAmount(discountAmount)
                .orderDetails(orderDetails)
                .orderAddress(orderAddress)
                .build();
    }
}



