package colorful.starbucks.order.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class OrderCursorResponseVo {

    private Long id;
    private LocalDateTime createdAt;
    private Integer totalAmount;
    private Integer discountAmount;

    @Builder
    private OrderCursorResponseVo(Long id,
                                  LocalDateTime createdAt,
                                  Integer totalAmount,
                                  Integer discountAmount)  {
        this.id = id;
        this.createdAt = createdAt;
        this.totalAmount = totalAmount;
        this.discountAmount = discountAmount;
    }


}



