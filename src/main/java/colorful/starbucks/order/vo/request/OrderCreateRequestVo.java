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
    private String memberAddressUuid;
    private String receiverName;
    private String memberUuid;
    private List<OrderDetailCreateRequestVo> orderDetails;

    @Builder
    private OrderCreateRequestVo(Long orderCode,
                                 String couponUuid,
                                 Integer totalAmount,
                                 Integer discountAmount,
                                 String memberAddressUuid,
                                 String receiverName,
                                 String memberUuid,
                                 List<OrderDetailCreateRequestVo> orderDetails) {
        this.orderCode = orderCode;
        this.couponUuid = couponUuid;
        this.totalAmount = totalAmount;
        this.discountAmount = discountAmount;
        this.memberAddressUuid = memberAddressUuid;
        this.receiverName = receiverName;
        this.memberUuid = memberUuid;
        this.orderDetails = orderDetails;
    }
}
