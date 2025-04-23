package colorful.starbucks.order.vo.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PreOrderRequestVo {

    private Integer totalAmount;
    private String memberAddressUuid;
    private String receiverName;
}
