package colorful.starbucks.event.dto.response;

import colorful.starbucks.event.domain.Event;
import colorful.starbucks.event.vo.response.EventResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EventResponseDto {

    private String eventUuid;
    private String title;
    private String thumbnailUrl;

    @Builder
    private EventResponseDto(String eventUuid, String title, String thumbnailUrl) {
        this.eventUuid = eventUuid;
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
    }

    public static EventResponseDto from(Event event) {
        return EventResponseDto.builder()
                .eventUuid(event.getEventUuid())
                .title(event.getTitle())
                .thumbnailUrl(event.getThumbnailUrl())
                .build();
    }

    public EventResponseVo toVo() {
        return EventResponseVo.builder()
                .eventUuid(eventUuid)
                .title(title)
                .thumbnailUrl(thumbnailUrl)
                .build();
    }
}
