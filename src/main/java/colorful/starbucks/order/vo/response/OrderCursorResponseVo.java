package colorful.starbucks.order.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class OrderCursorResponseVo {

    private Long orderCode;
    private LocalDateTime createdAt;
    private Integer totalAmount;
    private Integer discountAmount;

    @Builder
    private OrderCursorResponseVo(Long orderCode,
                                  LocalDateTime createdAt,
                                  Integer totalAmount,
                                  Integer discountAmount)  {
        this.orderCode = orderCode;
        this.createdAt = createdAt;
        this.totalAmount = totalAmount;
        this.discountAmount = discountAmount;
    }


}



