package colorful.starbucks.delivery.vo.request;

import lombok.Getter;

@Getter
public class DeliveryDefaultAddressRequestVo {

    private String memberAddressUuid;
    private boolean defaultAddress;
}
