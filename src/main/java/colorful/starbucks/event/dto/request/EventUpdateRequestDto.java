package colorful.starbucks.event.dto.request;

import colorful.starbucks.event.domain.Event;
import colorful.starbucks.event.domain.EventStatus;
import colorful.starbucks.event.vo.request.EventCreateRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class EventUpdateRequestDto {

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
    private EventUpdateRequestDto(String eventUuid,
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

    public static EventUpdateRequestDto of(String eventUuid,
                                           EventCreateRequestVo eventCreateRequestVo) {
        return EventUpdateRequestDto.builder()
                .eventUuid(eventUuid)
                .title(eventCreateRequestVo.getTitle())
                .description(eventCreateRequestVo.getDescription())
                .imageUrl(eventCreateRequestVo.getImageUrl())
                .thumbnailUrl(eventCreateRequestVo.getThumbnailUrl())
                .startDate(eventCreateRequestVo.getStartDate())
                .endDate(eventCreateRequestVo.getEndDate())
                .policy(eventCreateRequestVo.getPolicy())
                .status(eventCreateRequestVo.getStatus())
                .build();
    }

    public Event toEntity(Long eventId) {
        return Event.builder()
                .id(eventId)
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
