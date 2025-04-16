package colorful.starbucks.event.dto.response;

import colorful.starbucks.event.domain.EventProduct;
import colorful.starbucks.event.vo.response.EventProductCodesResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EventProductCodesResponseDto {

    private Long productCode;

    @Builder
    private EventProductCodesResponseDto(Long productCode) {
        this.productCode = productCode;
    }

    public static EventProductCodesResponseDto from(EventProduct eventProduct) {
        return EventProductCodesResponseDto.builder()
                .productCode(eventProduct.getProductCode())
                .build();
    }

    public EventProductCodesResponseVo toVo() {
        return EventProductCodesResponseVo.builder()
                .productCode(productCode)
                .build();
    }
}
