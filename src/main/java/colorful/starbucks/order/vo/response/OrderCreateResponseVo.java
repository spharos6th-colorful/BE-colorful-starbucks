package colorful.starbucks.order.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderCreateResponseVo {

    private Long orderCode;
    private Integer totalAmount;
    private Long deliveryAddressId;
    private String buyer;

    @Builder
    private OrderCreateResponseVo(Long orderCode,
                                  Integer totalAmount,
                                  Long deliveryAddressId,
                                  String buyer) {
        this.totalAmount = totalAmount;
        this.orderCode = orderCode;
        this.deliveryAddressId = deliveryAddressId;
        this.buyer = buyer;
    }

}
