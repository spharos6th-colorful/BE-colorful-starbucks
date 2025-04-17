package colorful.starbucks.order.vo.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderCancelRequestVo {
    private Long orderCode;
    private String orderCancelReason;
    private String orderCancelReasonDetail;
    private String memberUuid;

    @Builder
    private OrderCancelRequestVo(Long orderCode,
                                 String orderCancelReason,
                                 String orderCancelReasonDetail,
                                 String memberUuid) {
        this.orderCode = orderCode;
        this.orderCancelReason = orderCancelReason;
        this.orderCancelReasonDetail = orderCancelReasonDetail;
        this.memberUuid = memberUuid;
    }

}
