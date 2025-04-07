package colorful.starbucks.order.dto;

import colorful.starbucks.order.vo.OrderListFilterVo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OrderListFilterDto {
    private Long cursor;
    private Integer size;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String sortBy;

    @Builder
    private OrderListFilterDto(Long cursor,
                               Integer size,
                               LocalDateTime startDate,
                               LocalDateTime endDate,
                               String sortBy) {
        this.cursor = cursor;
        this.size = size;
        this.startDate = startDate;
        this.endDate = endDate;
        this.sortBy = sortBy;
    }

    public static OrderListFilterDto of(OrderListFilterVo orderListFilterVo, String memberUuid) {
        return OrderListFilterDto.builder()
                .cursor(orderListFilterVo.getCursor())
                .size(orderListFilterVo.getSize())
                .startDate(orderListFilterVo.getStartDate())
                .endDate(orderListFilterVo.getEndDate())
                .sortBy(orderListFilterVo.getSortBy())
                .build();
    }



}
