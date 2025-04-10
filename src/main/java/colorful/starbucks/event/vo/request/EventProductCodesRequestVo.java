package colorful.starbucks.event.vo.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EventProductCodesRequestVo {

    private String eventUuid;
    private Long cursor;
    private Integer page;
    private Integer size;
}
