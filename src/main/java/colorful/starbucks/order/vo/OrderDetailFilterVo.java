package colorful.starbucks.order.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderDetailFilterVo {

    private Long cursor;
    private Integer size;


}
