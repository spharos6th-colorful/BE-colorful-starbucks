package colorful.starbucks.event.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
public class EventResponseVo {

    private String eventUuid;
    private String title;
    private String thumbnailUrl;

    @Builder
    private EventResponseVo(String eventUuid,
                            String title,
                            String thumbnailUrl) {
        this.eventUuid = eventUuid;
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
    }
}
