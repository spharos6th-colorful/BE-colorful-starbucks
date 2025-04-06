package colorful.starbucks.event.dto.request;

import colorful.starbucks.event.vo.request.EventProductCodesRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class EventProductCodesRequestDto {

    private String eventUuid;
    private Integer page;
    private Integer size;

    @Builder
    private EventProductCodesRequestDto(String eventUuid,
                                        Integer page,
                                        Integer size) {
        this.eventUuid = eventUuid;
        this.page = page;
        this.size = size;
    }

    public static EventProductCodesRequestDto from(EventProductCodesRequestVo eventProductCodesRequestVo) {
        return EventProductCodesRequestDto.builder()
                .eventUuid(eventProductCodesRequestVo.getEventUuid())
                .page(eventProductCodesRequestVo.getPage())
                .size(eventProductCodesRequestVo.getSize())
                .build();
    }
}
