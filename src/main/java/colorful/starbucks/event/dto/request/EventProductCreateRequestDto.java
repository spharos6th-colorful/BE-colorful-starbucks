package colorful.starbucks.event.dto.request;

import colorful.starbucks.event.domain.EventProduct;
import colorful.starbucks.event.vo.request.EventProductCreateRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class EventProductCreateRequestDto {

    private String eventUuid;
    private Long productCode;

    @Builder
    private EventProductCreateRequestDto(String eventUuid,
                                         Long productCode) {
        this.eventUuid = eventUuid;
        this.productCode = productCode;
    }

    public static EventProductCreateRequestDto from(EventProductCreateRequestVo eventProductCreateRequestVo) {
        return EventProductCreateRequestDto.builder()
                .eventUuid(eventProductCreateRequestVo.getEventUuid())
                .productCode(eventProductCreateRequestVo.getProductCode())
                .build();
    }

    public EventProduct toEntity() {
        return EventProduct.builder()
                .eventUuid(eventUuid)
                .productCode(productCode)
                .build();
    }
}
