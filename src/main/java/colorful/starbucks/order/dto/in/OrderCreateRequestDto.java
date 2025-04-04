package colorful.starbucks.order.dto.in;

import colorful.starbucks.order.domain.Order;
import colorful.starbucks.order.domain.OrderStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderCreateRequestDto {

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
    private OrderCreateRequestDto(String couponUuid,
                                 int totalAmount,
                                 int discountAmount,
                                 String zoneCode,
                                 String address,
                                 String detailAddress,
                                 Boolean isGift,
                                 String buyer,
                                 List<OrderDetailRequestDto> orderDetails) {
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
    public Order toEntity(){
        return Order.builder()
                .couponUuid(couponUuid)
                .totalAmount(totalAmount)
                .discountAmount(discountAmount)
                .address(address)
                .detailAddress(detailAddress)
                .isGift(isGift)
                .buyer(buyer)
                .build();
    }


}
