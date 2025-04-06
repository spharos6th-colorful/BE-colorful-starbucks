package colorful.starbucks.order.vo.response;

import colorful.starbucks.order.vo.OrderAddressVo;
import lombok.Builder;

import lombok.Getter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class OrderListResponseVo {

    private LocalDateTime orderDate;
    private Long orderCode;
    private int totalAmount;
    private int discountAmount;
    private List<OrderDetailResponseVo> orderDetails;
    private List<OrderAddressVo> orderAddresses;

    @Builder
    private OrderListResponseVo(LocalDateTime orderDate,
                                Long orderCode,
                                int totalAmount,
                                int discountAmount,
                                List<OrderDetailResponseVo> orderDetails,
                                List<OrderAddressVo> orderAddresses) {
        this.orderDate = orderDate;
        this.orderCode = orderCode;
        this.totalAmount = totalAmount;
        this.discountAmount = discountAmount;
        this.orderDetails = orderDetails;
        this.orderAddresses = orderAddresses;
    }



}
