package colorful.starbucks.order.vo.response;

import colorful.starbucks.order.vo.OrderAddressVo;
import lombok.Builder;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class OrderListResponseVo {

    private LocalDateTime createdAt;
    private Long orderCode;
    private int totalAmount;
    private int discountAmount;
    private List<OrderDetailResponseVo> orderDetails;
    private OrderAddressVo OrderAddress;

    @Builder
    private OrderListResponseVo(LocalDateTime createdAt,
                                Long orderCode,
                                int totalAmount,
                                int discountAmount,
                                List<OrderDetailResponseVo> orderDetails,
                                OrderAddressVo OrderAddress) {
        this.createdAt = createdAt;
        this.orderCode = orderCode;
        this.totalAmount = totalAmount;
        this.discountAmount = discountAmount;
        this.orderDetails = orderDetails;
        this.OrderAddress = OrderAddress;
    }



}
