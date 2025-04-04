package colorful.starbucks.order.vo.in;


import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderCreateRequestVo {

    private Long orderCode;

    private String couponUuid;

    private int totalAmount;

    private int discountAmount;

    private String zoneCode;

    private String address;

    private String detailAddress;

    private Boolean isGift;

    private String buyer;

    private List<OrderDetailRequestVo> orderDetails;

    @Builder
    private OrderCreateRequestVo(Long orderCode,
                                 String couponUuid,
                                 int totalAmount,
                                 int discountAmount,
                                 String zoneCode,
                                 String address,
                                 String detailAddress,
                                 Boolean isGift,
                                 String buyer,
                                 List<OrderDetailRequestVo> orderDetails) {
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



}
