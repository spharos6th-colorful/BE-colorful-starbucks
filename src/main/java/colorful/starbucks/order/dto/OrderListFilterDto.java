package colorful.starbucks.order.dto;

import colorful.starbucks.order.vo.OrderListFilterVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderListFilterDto {
    private Long cursor;
    private Integer size;
    private String startDate;
    private String endDate;

    @Builder
    private OrderListFilterDto(Long cursor,
                               Integer size,
                               String startDate,
                               String endDate) {
        this.cursor = cursor;
        this.size = size;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static OrderListFilterDto of(OrderListFilterVo orderListFilterVo, String memberUuid) {
        return OrderListFilterDto.builder()
                .cursor(orderListFilterVo.getCursor())
                .size(orderListFilterVo.getSize())
                .startDate(orderListFilterVo.getStartDate())
                .endDate(orderListFilterVo.getEndDate())
                .build();
    }



}
