package colorful.starbucks.order.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderCreateResponseVo {

    private Long orderCode;
    private Integer totalAmount;
    private String memberAddressUuid;
    private String receiverName;

    @Builder
    private OrderCreateResponseVo(Long orderCode,
                                  Integer totalAmount,
                                  String memberAddressUuid,
                                  String receiverName) {
        this.totalAmount = totalAmount;
        this.orderCode = orderCode;
        this.memberAddressUuid = memberAddressUuid;
        this.receiverName = receiverName;
    }

}
