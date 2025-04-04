package colorful.starbucks.event.vo.request;

import colorful.starbucks.event.domain.EventStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EventFilterRequestVo {

    private EventStatus status;
    private Integer page;
    private Integer size;
    private Boolean isDeleted;
}
