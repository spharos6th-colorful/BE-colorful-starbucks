package colorful.starbucks.order.dto;

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
}
