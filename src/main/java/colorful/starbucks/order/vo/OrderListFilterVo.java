package colorful.starbucks.order.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderListFilterVo {
    private Long cursor;
    private Integer size;
    private String startDate;
    private String endDate;
}
