package colorful.starbucks.order.vo.response;

import colorful.starbucks.order.vo.OrderAddressVo;
import lombok.Builder;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class OrderResponseVo {

    private Long id;
    private LocalDateTime createdAt;
    private Long orderCode;
    private Integer totalAmount;
    private Integer discountAmount;
    private List<OrderDetailResponseVo> orderDetails;
    private OrderAddressVo OrderAddress;

    @Builder
    private OrderResponseVo(Long id,
                            LocalDateTime createdAt,
                            Long orderCode,
                            Integer totalAmount,
                            Integer discountAmount,
                            List<OrderDetailResponseVo> orderDetails,
                            OrderAddressVo OrderAddress) {
        this.id = id;
        this.createdAt = createdAt;
        this.orderCode = orderCode;
        this.totalAmount = totalAmount;
        this.discountAmount = discountAmount;
        this.orderDetails = orderDetails;
        this.OrderAddress = OrderAddress;
    }


}
