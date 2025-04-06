package colorful.starbucks.event.dto.response;

import colorful.starbucks.event.vo.response.EventProductResponseVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class EventProductResponseDto {

    private Long productCode;

    @Builder
    private EventProductResponseDto(Long productCode) {
        this.productCode = productCode;
    }

    public static EventProductResponseDto from(Long productCode) {
        return EventProductResponseDto.builder()
                .productCode(productCode)
                .build();
    }

    public EventProductResponseVo toVo() {
        return EventProductResponseVo.builder()
                .productCode(productCode)
                .build();
    }
}
