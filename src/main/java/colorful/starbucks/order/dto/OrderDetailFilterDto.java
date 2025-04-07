package colorful.starbucks.order.dto;

import colorful.starbucks.order.vo.OrderDetailFilterVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderDetailFilterDto {
    private Long cursor;
    private Integer size;

    @Builder
    private OrderDetailFilterDto(Long cursor,
                                 Integer size) {
        this.cursor = cursor;
        this.size = size;
    }

    public static OrderDetailFilterDto of(OrderDetailFilterVo orderDetailFilterVo,
                                          String memberUuid)  {
        return OrderDetailFilterDto.builder()
                .cursor(orderDetailFilterVo.getCursor())
                .size(orderDetailFilterVo.getSize())
                .build();
    }
}
