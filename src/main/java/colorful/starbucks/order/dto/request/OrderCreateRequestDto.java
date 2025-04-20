package colorful.starbucks.order.dto.request;

import colorful.starbucks.order.domain.Order;
import colorful.starbucks.order.domain.OrderStatus;
import colorful.starbucks.order.vo.request.OrderCreateRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class OrderCreateRequestDto {

    private Long orderCode;
    private String couponUuid;
    private Integer totalAmount;
    private Integer discountAmount;
    private String memberAddressUuid;
    private String buyer;
    private String memberUuid;
    private List<OrderDetailCreateRequestDto> orderDetails;

    @Builder
    private OrderCreateRequestDto(Long orderCode,
                                  String couponUuid,
                                  Integer totalAmount,
                                  Integer discountAmount,
                                  String memberAddressUuid,
                                  String buyer,
                                  String memberUuid,
                                  List<OrderDetailCreateRequestDto> orderDetails) {
        this.orderCode = orderCode;
        this.couponUuid = couponUuid;
        this.totalAmount = totalAmount;
        this.discountAmount = discountAmount;
        this.memberAddressUuid = memberAddressUuid;
        this.buyer = buyer;
        this.memberUuid = memberUuid;
        this.orderDetails = orderDetails;
    }

    public Order toEntity(Long orderCode, String zoneCode, String address, String detailAddress) {
        return Order.builder()
                .orderCode(orderCode)
                .zoneCode(zoneCode)
                .address(address)
                .detailAddress(detailAddress)
                .couponUuid(couponUuid)
                .totalAmount(totalAmount)
                .discountAmount(discountAmount)
                .buyer(buyer)
                .memberUuid(memberUuid)
                .orderStatus(OrderStatus.PAID)
                .orderCancelReason(null)
                .orderCancelReasonDetail(null)
                .build();
    }

    public static OrderCreateRequestDto of(OrderCreateRequestVo orderCreateRequestVo, String memberUuid) {
        return OrderCreateRequestDto.builder()
                .orderCode(orderCreateRequestVo.getOrderCode())
                .couponUuid(orderCreateRequestVo.getCouponUuid())
                .totalAmount(orderCreateRequestVo.getTotalAmount())
                .discountAmount(orderCreateRequestVo.getDiscountAmount())
                .buyer(orderCreateRequestVo.getReceiverName())
                .memberUuid(memberUuid)
                .orderDetails(orderCreateRequestVo.getOrderDetails().stream()
                        .map(OrderDetailCreateRequestDto::of)
                        .toList())
                .build();
    }







}
