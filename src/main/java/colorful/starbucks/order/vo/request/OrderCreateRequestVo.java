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
    private Long deliveryAddressId;
    private String buyer;
    private String memberUuid;
    private List<OrderCreateDetailRequestVo> orderDetails;

    @Builder
    private OrderCreateRequestVo(Long orderCode,
                                 String couponUuid,
                                 Integer totalAmount,
                                 Integer discountAmount,
                                 Long deliveryAddressId,
                                 String buyer,
                                 String memberUuid,
                                 List<OrderCreateDetailRequestVo> orderDetails) {
        this.orderCode = orderCode;
        this.couponUuid = couponUuid;
        this.totalAmount = totalAmount;
        this.discountAmount = discountAmount;
        this.deliveryAddressId = deliveryAddressId;
        this.buyer = buyer;
        this.memberUuid = memberUuid;
        this.orderDetails = orderDetails;
    }


}
