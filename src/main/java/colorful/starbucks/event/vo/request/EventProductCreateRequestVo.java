package colorful.starbucks.event.vo.request;

import lombok.Getter;

@Getter
public class EventProductCreateRequestVo {

    private String eventUuid;
    private Long productCode;
    private String productThumbnailUrl;
    private String productName;
    private int price;
}
