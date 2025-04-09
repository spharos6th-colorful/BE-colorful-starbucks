package colorful.starbucks.event.dto.request;

import colorful.starbucks.event.vo.request.EventProductCodesRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class EventProductCodesRequestDto {

    private String eventUuid;
    private Long cursor;
    private Integer page;
    private Integer size;

    @Builder
    private EventProductCodesRequestDto(String eventUuid,
                                        Long cursor,
                                        Integer page,
                                        Integer size) {
        this.eventUuid = eventUuid;
        this.cursor = cursor;
        this.page = page;
        this.size = size;
    }

    public static EventProductCodesRequestDto from(EventProductCodesRequestVo eventProductCodesRequestVo) {
        return EventProductCodesRequestDto.builder()
                .eventUuid(eventProductCodesRequestVo.getEventUuid())
                .cursor(eventProductCodesRequestVo.getCursor())
                .page(eventProductCodesRequestVo.getPage())
                .size(eventProductCodesRequestVo.getSize())
                .build();
    }
}
