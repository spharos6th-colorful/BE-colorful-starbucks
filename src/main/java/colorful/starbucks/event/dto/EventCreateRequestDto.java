package colorful.starbucks.event.dto;

import colorful.starbucks.event.domain.Event;
import colorful.starbucks.event.domain.EventStatus;
import colorful.starbucks.event.vo.EventCreateRequestVo;
import colorful.starbucks.product.generator.ProductDetailCodeGenerator;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class EventCreateRequestDto {

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
    private EventCreateRequestDto(String eventUuid,
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

    public static EventCreateRequestDto from(EventCreateRequestVo eventCreateRequestVo) {
        return EventCreateRequestDto.builder()
                .eventUuid(ProductDetailCodeGenerator.generate())
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

    public Event toEntity() {
        return Event.builder()
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
