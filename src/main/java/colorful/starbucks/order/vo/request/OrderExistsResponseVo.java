package colorful.starbucks.order.vo.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderExistsResponseVo {

    private Boolean exists;

    @Builder
    private OrderExistsResponseVo(Boolean exists) {
        this.exists = exists;
    }
}
