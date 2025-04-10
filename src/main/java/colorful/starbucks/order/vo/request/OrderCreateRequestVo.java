package colorful.starbucks.order.vo.request;


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
    private String memberUuid;
    private List<OrderCreateDetailRequestVo> orderDetails;

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
                                    String memberUuid,
                                 List<OrderCreateDetailRequestVo> orderDetails) {
        this.orderCode = orderCode;
        this.couponUuid = couponUuid;
        this.totalAmount = totalAmount;
        this.discountAmount = discountAmount;
        this.zoneCode = zoneCode;
        this.address = address;
        this.detailAddress = detailAddress;
        this.isGift = isGift;
        this.buyer = buyer;
        this.memberUuid = memberUuid;
        this.orderDetails = orderDetails;
    }



}
