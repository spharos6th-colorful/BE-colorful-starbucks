package colorful.starbucks.event.vo.response;

import colorful.starbucks.event.domain.EventStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class EventDetailResponseVo {

    private String title;
    private String description;
    private String imageUrl;
    private String thumbnailUrl;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String policy;
    private EventStatus status;

    @Builder
    private EventDetailResponseVo(String title,
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
}
