package colorful.starbucks.order.dto.response;


import colorful.starbucks.order.dto.OrderAddressDto;
import colorful.starbucks.order.vo.response.OrderListResponseVo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class OrderListResponseDto {

    private LocalDateTime createdAt;
    private Long orderCode;
    private int totalAmount;
    private int discountAmount;
    private List<OrderDetailResponseDto> orderDetails;
    private OrderAddressDto orderAddress;

    @Builder
    private OrderListResponseDto(LocalDateTime createdAt,
                                 Long orderCode,
                                 int totalAmount,
                                 int discountAmount,
                                 List<OrderDetailResponseDto> orderDetails,
                                 OrderAddressDto orderAddress) {
        this.createdAt = createdAt;
        this.orderCode = orderCode;
        this.totalAmount = totalAmount;
        this.discountAmount = discountAmount;
        this.orderDetails = orderDetails;
        this.orderAddress = orderAddress;
    }

    public OrderListResponseVo toVo(){
        return OrderListResponseVo.builder()
                .createdAt(createdAt)
                .orderCode(orderCode)
                .totalAmount(totalAmount)
                .discountAmount(discountAmount)
                .orderDetails(orderDetails.stream().map(OrderDetailResponseDto::toVo).toList())
                .OrderAddress(orderAddress.toVo())
                .build();
    }

    public static OrderListResponseDto of(LocalDateTime createdAt,
                                          Long orderCode,
                                          int totalAmount,
                                          int discountAmount,
                                          List<OrderDetailResponseDto> orderDetails,
                                          OrderAddressDto orderAddress) {
        return OrderListResponseDto.builder()
                .createdAt(createdAt)
                .orderCode(orderCode)
                .totalAmount(totalAmount)
                .discountAmount(discountAmount)
                .orderDetails(orderDetails)
                .orderAddress(orderAddress)
                .build();
    }


}
