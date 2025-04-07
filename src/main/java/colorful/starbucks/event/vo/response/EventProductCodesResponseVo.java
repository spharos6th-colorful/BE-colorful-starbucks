package colorful.starbucks.event.vo.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class EventProductCodesResponseVo {
    private Long productCode;

    @Builder
    private EventProductCodesResponseVo(Long productCode) {
        this.productCode = productCode;
    }
}
