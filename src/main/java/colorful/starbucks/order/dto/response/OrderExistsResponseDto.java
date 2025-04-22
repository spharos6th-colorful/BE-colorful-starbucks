package colorful.starbucks.order.dto.response;

import colorful.starbucks.order.vo.request.OrderExistsResponseVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderExistsResponseDto {

    private Boolean exists;

    @Builder
    private OrderExistsResponseDto(Boolean exists) {
        this.exists = exists;
    }

    public static OrderExistsResponseDto from(Boolean exists) {
        return new OrderExistsResponseDto(exists);
    }

    public OrderExistsResponseVo toVo() {
        return OrderExistsResponseVo.builder()
                .exists(exists)
                .build();
    }
}
