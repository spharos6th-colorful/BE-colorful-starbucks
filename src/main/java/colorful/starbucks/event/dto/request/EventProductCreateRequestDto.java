package colorful.starbucks.event.dto.request;

import colorful.starbucks.event.domain.EventProduct;
import colorful.starbucks.event.vo.request.EventProductCreateRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class EventProductCreateRequestDto {

    private String eventUuid;
    private Long productCode;
    private String productThumbnailUrl;
    private String productName;
    private int price;

    @Builder
    private EventProductCreateRequestDto(String eventUuid,
                                         Long productCode,
                                         String productThumbnailUrl,
                                         String productName,
                                         int price) {
        this.eventUuid = eventUuid;
        this.productCode = productCode;
        this.productThumbnailUrl = productThumbnailUrl;
        this.productName = productName;
        this.price = price;
    }

    public static EventProductCreateRequestDto from(EventProductCreateRequestVo eventProductCreateRequestVo) {
        return EventProductCreateRequestDto.builder()
                .eventUuid(eventProductCreateRequestVo.getEventUuid())
                .productCode(eventProductCreateRequestVo.getProductCode())
                .productThumbnailUrl(eventProductCreateRequestVo.getProductThumbnailUrl())
                .productName(eventProductCreateRequestVo.getProductName())
                .price(eventProductCreateRequestVo.getPrice())
                .build();
    }

    public EventProduct toEntity() {
        return EventProduct.builder()
                .eventUuid(eventUuid)
                .productCode(productCode)
                .productThumbnailUrl(productThumbnailUrl)
                .productName(productName)
                .price(price)
                .build();
    }
}
