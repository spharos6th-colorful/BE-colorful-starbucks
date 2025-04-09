package colorful.starbucks.event.dto.response;

import colorful.starbucks.event.domain.Event;
import colorful.starbucks.event.domain.EventStatus;
import colorful.starbucks.event.vo.response.EventDetailResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class EventDetailResponseDto {

    private String title;
    private String description;
    private String imageUrl;
    private String thumbnailUrl;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String policy;
    private EventStatus status;

    @Builder
    private EventDetailResponseDto(String title,
                                   String description,
                                   String imageUrl,
                                   String thumbnailUrl,
                                   LocalDateTime startDate,
                                   LocalDateTime endDate,
                                   String policy,
                                   EventStatus status) {
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
