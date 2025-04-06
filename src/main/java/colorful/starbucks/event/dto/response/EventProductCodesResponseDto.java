package colorful.starbucks.event.dto.response;

import colorful.starbucks.event.vo.response.EventProductCodesResponseVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class EventProductCodesResponseDto {

    private Long productCode;

    @Builder
    private EventProductCodesResponseDto(Long productCode) {
        this.productCode = productCode;
    }

    public static EventProductCodesResponseDto from(Long productCode) {
        return EventProductCodesResponseDto.builder()
                .productCode(productCode)
                .build();
    }

    public EventProductCodesResponseVo toVo() {
        return EventProductCodesResponseVo.builder()
                .productCode(productCode)
                .build();
    }
}
