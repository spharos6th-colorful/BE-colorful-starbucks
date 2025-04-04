package colorful.starbucks.order.dto.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderCreateResponseDto {

    private String orderCode;

    @Builder
    private OrderCreateResponseDto(String orderCode) {
        this.orderCode = orderCode;
    }
}
