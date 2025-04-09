package colorful.starbucks.order.dto;

import colorful.starbucks.order.vo.OrderListFilterVo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OrderListFilterDto {
    private Long cursor;
    private Integer size;
    private Integer page;
    private LocalDateTime startDate;
    private LocalDateTime endDate;


    @Builder
    private OrderListFilterDto(Long cursor,
                               Integer size,
                               Integer page,
                               LocalDateTime startDate,
                               LocalDateTime endDate) {
        this.cursor = cursor;
        this.size = size;
        this.page = page;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static OrderListFilterDto of(OrderListFilterVo orderListFilterVo, String memberUuid) {
        return OrderListFilterDto.builder()
                .cursor(orderListFilterVo.getCursor())
                .size(orderListFilterVo.getSize())
                .page(orderListFilterVo.getPage())
                .startDate(orderListFilterVo.getStartDate())
                .endDate(orderListFilterVo.getEndDate())
                .build();
    }



}
