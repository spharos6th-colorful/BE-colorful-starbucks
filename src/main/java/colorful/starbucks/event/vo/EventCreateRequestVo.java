package colorful.starbucks.event.vo;

import colorful.starbucks.event.domain.EventStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class EventCreateRequestVo {

    private String title;
    private String description;
    private String imageUrl;
    private String thumbnailUrl;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String policy;
    private EventStatus status;
}
