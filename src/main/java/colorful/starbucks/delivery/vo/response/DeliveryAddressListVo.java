package colorful.starbucks.delivery.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class DeliveryAddressListVo {

    private List<DeliveryAddressResponseVo> deliveryAddresses;

    @Builder
    private DeliveryAddressListVo(List<DeliveryAddressResponseVo> deliveryAddresses) {
        this.deliveryAddresses = deliveryAddresses;
    }
}
