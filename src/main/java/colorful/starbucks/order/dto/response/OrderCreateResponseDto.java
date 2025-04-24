package colorful.starbucks.order.dto.response;

import colorful.starbucks.delivery.domain.DeliveryAddress;
import colorful.starbucks.order.domain.Order;
import colorful.starbucks.order.vo.response.OrderCreateResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderCreateResponseDto {

    private Long orderCode;
    private Integer totalAmount;
    private String memberAddressUuid;
    private String receiverName;

    @Builder
    private OrderCreateResponseDto(Long orderCode, Integer totalAmount, String memberAddressUuid, String receiverName) {
        this.orderCode = orderCode;
        this.totalAmount = totalAmount;
        this.memberAddressUuid = memberAddressUuid;
        this.receiverName = receiverName;
    }

    public static OrderCreateResponseDto from(Order order, DeliveryAddress deliveryAddress) {
        return OrderCreateResponseDto.builder()
                .orderCode(order.getOrderCode())
                .totalAmount(order.getTotalAmount())
                .memberAddressUuid(deliveryAddress.getMemberAddressUuid())
                .receiverName(order.getBuyer())
                .build();
    }

    public OrderCreateResponseVo toVo() {
        return OrderCreateResponseVo.builder()
                .orderCode(orderCode)
                .totalAmount(totalAmount)
                .memberAddressUuid(memberAddressUuid)
                .receiverName(receiverName)
                .build();
    }
}
