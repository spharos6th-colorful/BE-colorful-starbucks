package colorful.starbucks.order.dto.in;

import colorful.starbucks.order.domain.Order;
import colorful.starbucks.order.vo.in.OrderCreateRequestVo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderCreateRequestDto {

    private Long orderCode;
    private String couponUuid;
    private int totalAmount;
    private int discountAmount;
    private String zoneCode;
    private String address;
    private String detailAddress;
    private Boolean isGift;
    private String buyer;
    private List<OrderDetailRequestDto> orderDetails;

    @Builder
    private OrderCreateRequestDto(Long orderCode,
                                 String couponUuid,
                                 int totalAmount,
                                 int discountAmount,
                                 String zoneCode,
                                 String address,
                                 String detailAddress,
                                 Boolean isGift,
                                 String buyer,
                                 List<OrderDetailRequestDto> orderDetails) {
        this.orderCode = orderCode;
        this.couponUuid = couponUuid;
        this.totalAmount = totalAmount;
        this.discountAmount = discountAmount;
        this.zoneCode = zoneCode;
        this.address = address;
        this.detailAddress = detailAddress;
        this.isGift = isGift;
        this.buyer = buyer;
        this.orderDetails = orderDetails;
    }
    public Order toEntity(Long orderCode){
        return Order.builder()
                .orderCode(orderCode)
                .couponUuid(couponUuid)
                .totalAmount(totalAmount)
                .discountAmount(discountAmount)
                .address(address)
                .detailAddress(detailAddress)
                .isGift(isGift)
                .buyer(buyer)
                .build();
    }
    public static OrderCreateRequestDto of(OrderCreateRequestVo orderCreateRequestVo, String memberUuid) {
        return OrderCreateRequestDto.builder()
                .orderCode(orderCreateRequestVo.getOrderCode())
                .couponUuid(orderCreateRequestVo.getCouponUuid())
                .totalAmount(orderCreateRequestVo.getTotalAmount())
                .discountAmount(orderCreateRequestVo.getDiscountAmount())
                .zoneCode(orderCreateRequestVo.getZoneCode())
                .address(orderCreateRequestVo.getAddress())
                .detailAddress(orderCreateRequestVo.getDetailAddress())
                .isGift(orderCreateRequestVo.getIsGift())
                .buyer(orderCreateRequestVo.getBuyer())
                .orderDetails(orderCreateRequestVo.getOrderDetails().stream()
                        .map(OrderDetailRequestDto::of)
                        .toList())
                .build();
    }



}
