package colorful.starbucks.event.vo.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EventProductFilterRequestVo {

    private String eventUuid;
    private Integer page;
    private Integer size;
}
