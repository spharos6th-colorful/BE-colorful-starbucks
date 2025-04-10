package colorful.starbucks.order.dto.request;

import colorful.starbucks.order.domain.OrderCancelReason;
import colorful.starbucks.order.vo.request.OrderCancelRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderCancelRequestDto {
    private Long orderCode;
    private OrderCancelReason orderCancelReason;
    private String orderCancelReasonDetail;
    private String memberUuid;

    @Builder
    private OrderCancelRequestDto(Long orderCode,
                                   OrderCancelReason orderCancelReason,
                                   String orderCancelReasonDetail,
                                   String memberUuid) {
        this.orderCode = orderCode;
        this.orderCancelReason = orderCancelReason;
        this.orderCancelReasonDetail = orderCancelReasonDetail;
        this.memberUuid = memberUuid;
    }

    public static OrderCancelRequestDto of(OrderCancelRequestVo orderCancelRequestVo, String memberUuid) {
        return OrderCancelRequestDto.builder()
                .orderCode(orderCancelRequestVo.getOrderCode())
                .orderCancelReason(OrderCancelReason.getResponse(orderCancelRequestVo.getOrderCancelReason()))
                .orderCancelReasonDetail(orderCancelRequestVo.getOrderCancelReasonDetail())
                .memberUuid(memberUuid)
                .build();
    }

    public OrderCancelRequestVo toVo() {
        return OrderCancelRequestVo.builder()
                .orderCode(orderCode)
                .orderCancelReason(orderCancelReason.name())
                .orderCancelReasonDetail(orderCancelReasonDetail)
                .memberUuid(memberUuid)
                .build();
    }
}
