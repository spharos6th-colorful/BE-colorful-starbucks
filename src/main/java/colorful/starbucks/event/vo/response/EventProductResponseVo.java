package colorful.starbucks.event.vo.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class EventProductResponseVo {
    private Long productCode;

    @Builder
    private EventProductResponseVo(Long productCode) {
        this.productCode = productCode;
    }
}
