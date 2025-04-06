package colorful.starbucks.event.dto.response;

import colorful.starbucks.event.vo.response.EventProductCodeResponseVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class EventProductCodeResponseDto {

    private Long productCode;

    @Builder
    private EventProductCodeResponseDto(Long productCode) {
        this.productCode = productCode;
    }

    public static EventProductCodeResponseDto from(Long productCode) {
        return EventProductCodeResponseDto.builder()
                .productCode(productCode)
                .build();
    }

    public EventProductCodeResponseVo toVo() {
        return EventProductCodeResponseVo.builder()
                .productCode(productCode)
                .build();
    }
}
