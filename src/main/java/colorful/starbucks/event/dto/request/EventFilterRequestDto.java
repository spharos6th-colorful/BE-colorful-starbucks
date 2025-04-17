package colorful.starbucks.event.dto.request;

import colorful.starbucks.event.domain.EventStatus;
import colorful.starbucks.event.vo.request.EventFilterRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EventFilterRequestDto {

    private EventStatus status;
    private Integer page;
    private Integer size;

    @Builder
    private EventFilterRequestDto(EventStatus status,
                                  Integer page,
                                  Integer size) {
        this.status = status;
        this.page = page;
        this.size = size;
    }

    public static EventFilterRequestDto from(EventFilterRequestVo eventFilterRequestVo) {
        return EventFilterRequestDto.builder()
                .status(eventFilterRequestVo.getStatus())
                .page(eventFilterRequestVo.getPage())
                .size(eventFilterRequestVo.getSize())
                .build();
    }
}
