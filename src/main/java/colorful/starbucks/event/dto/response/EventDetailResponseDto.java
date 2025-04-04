package colorful.starbucks.event.dto.response;

import colorful.starbucks.event.domain.Event;
import colorful.starbucks.event.domain.EventStatus;
import colorful.starbucks.event.vo.response.EventDetailResponseVo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class EventDetailResponseDto {

    private String eventUuid;
    private String title;
    private String description;
    private String imageUrl;
    private String thumbnailUrl;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String policy;
    private EventStatus status;

    @Builder
    private EventDetailResponseDto(String eventUuid,
                                   String title,
                                   String description,
                                   String imageUrl,
                                   String thumbnailUrl,
                                   LocalDateTime startDate,
                                   LocalDateTime endDate,
                                   String policy,
                                   EventStatus status) {
        this.eventUuid = eventUuid;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.thumbnailUrl = thumbnailUrl;
        this.startDate = startDate;
        this.endDate = endDate;
        this.policy = policy;
        this.status = status;
    }

    public static EventDetailResponseDto from(Event event) {
        return EventDetailResponseDto.builder()
                .eventUuid(event.getEventUuid())
                .title(event.getTitle())
                .description(event.getDescription())
                .imageUrl(event.getImageUrl())
                .thumbnailUrl(event.getThumbnailUrl())
                .startDate(event.getStartDate())
                .endDate(event.getEndDate())
                .policy(event.getPolicy())
                .status(event.getStatus())
                .build();
    }

    public EventDetailResponseVo toVo() {
        return EventDetailResponseVo.builder()
                .eventUuid(eventUuid)
                .title(title)
                .description(description)
                .imageUrl(imageUrl)
                .thumbnailUrl(thumbnailUrl)
                .startDate(startDate)
                .endDate(endDate)
                .policy(policy)
                .status(status)
                .build();
    }
}
