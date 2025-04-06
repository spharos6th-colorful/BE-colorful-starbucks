package colorful.starbucks.event.vo.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class EventProductCodeResponseVo {
    private Long productCode;

    @Builder
    private EventProductCodeResponseVo(Long productCode) {
        this.productCode = productCode;
    }
}
