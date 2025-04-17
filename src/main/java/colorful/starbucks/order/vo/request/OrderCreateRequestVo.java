package colorful.starbucks.order.vo.request;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class OrderCreateRequestVo {

    private Long orderCode;
    private String couponUuid;
    private Integer totalAmount;
    private Integer discountAmount;
    private String zoneCode;
    private String address;
    private String detailAddress;
    private String buyer;
    private String memberUuid;
    private List<OrderCreateDetailRequestVo> orderDetails;

    @Builder
    private OrderCreateRequestVo(Long orderCode,
                                 String couponUuid,
                                 Integer totalAmount,
                                 Integer discountAmount,
                                 String zoneCode,
                                 String address,
                                 String detailAddress,
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
        this.buyer = buyer;
        this.memberUuid = memberUuid;
        this.orderDetails = orderDetails;
    }



}
