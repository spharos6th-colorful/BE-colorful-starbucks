package colorful.starbucks.order.dto;

import colorful.starbucks.order.vo.OrderDetailFilterVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderDetailFilterDto {
    private Long cursor;
    private Integer size;
    private Integer page;

    @Builder
    private OrderDetailFilterDto(Long cursor,
                                 Integer size,
                                 Integer page) {
        this.cursor = cursor;
        this.size = size;
        this.page = page;
    }

    public static OrderDetailFilterDto of(OrderDetailFilterVo orderDetailFilterVo,
                                          String memberUuid)  {
        return OrderDetailFilterDto.builder()
                .cursor(orderDetailFilterVo.getCursor())
                .size(orderDetailFilterVo.getSize())
                .page(orderDetailFilterVo.getPage())
                .build();
    }
}
