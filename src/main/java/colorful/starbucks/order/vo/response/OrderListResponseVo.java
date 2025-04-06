package colorful.starbucks.order.vo.response;

import colorful.starbucks.order.vo.OrderAddressVo;
import lombok.Builder;

import lombok.Getter;
import java.util.List;

@Getter
public class OrderListResponseVo {

    private String orderDate;
    private Long orderCode;
    private int totalAmount;
    private int discountAmount;
    private List<OrderDetailResponseVo> orderDetails;
    private OrderAddressVo OrderAddress;

    @Builder
    private OrderListResponseVo(String orderDate,
                                Long orderCode,
                                int totalAmount,
                                int discountAmount,
                                List<OrderDetailResponseVo> orderDetails,
                                OrderAddressVo OrderAddress) {
        this.orderDate = orderDate;
        this.orderCode = orderCode;
        this.totalAmount = totalAmount;
        this.discountAmount = discountAmount;
        this.orderDetails = orderDetails;
        this.OrderAddress = OrderAddress;
    }



}
