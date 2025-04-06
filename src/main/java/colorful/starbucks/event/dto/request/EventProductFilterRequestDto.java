package colorful.starbucks.event.dto.request;

import colorful.starbucks.event.vo.request.EventProductFilterRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class EventProductFilterRequestDto {

    private String eventUuid;
    private Integer page;
    private Integer size;

    @Builder
    private EventProductFilterRequestDto(String eventUuid,
                                         Integer page,
                                         Integer size) {
        this.eventUuid = eventUuid;
        this.page = page;
        this.size = size;
    }

    public static EventProductFilterRequestDto from(EventProductFilterRequestVo eventProductFilterRequestVo) {
        return EventProductFilterRequestDto.builder()
                .eventUuid(eventProductFilterRequestVo.getEventUuid())
                .page(eventProductFilterRequestVo.getPage())
                .size(eventProductFilterRequestVo.getSize())
                .build();
    }
}
