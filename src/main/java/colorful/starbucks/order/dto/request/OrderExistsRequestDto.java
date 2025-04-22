package colorful.starbucks.order.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderExistsRequestDto {

    private Long orderCode;
    private String memberUuid;

    @Builder
    private OrderExistsRequestDto(Long orderCode, String memberUuid) {
        this.orderCode = orderCode;
        this.memberUuid = memberUuid;
    }

    public static OrderExistsRequestDto of(Long orderCode, String memberUuid) {
        return OrderExistsRequestDto.builder()
                .orderCode(orderCode)
                .memberUuid(memberUuid)
                .build();
    }
}
