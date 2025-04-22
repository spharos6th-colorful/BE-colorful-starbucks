package colorful.starbucks.order.dto;

import colorful.starbucks.order.vo.OrderDetailFilterVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderDetailFilterDto {
    private Long orderCode;
    private Long cursor;
    private Integer size;
    private Integer page;

    @Builder
    private OrderDetailFilterDto(Long orderCode,
                                 Long cursor,
                                 Integer size,
                                 Integer page) {
        this.orderCode = orderCode;
        this.cursor = cursor;
        this.size = size;
        this.page = page;
    }

    public static OrderDetailFilterDto of(OrderDetailFilterVo orderDetailFilterVo,
                                          Long orderCode) {
        return OrderDetailFilterDto.builder()
                .orderCode(orderCode)
                .cursor(orderDetailFilterVo.getCursor())
                .size(orderDetailFilterVo.getSize())
                .page(orderDetailFilterVo.getPage())
                .build();
    }
}
