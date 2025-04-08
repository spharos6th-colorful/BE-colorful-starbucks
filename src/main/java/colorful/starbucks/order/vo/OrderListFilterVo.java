package colorful.starbucks.order.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class OrderListFilterVo {
    private Long cursor;
    private Integer size;
    private Integer page;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

}

