package colorful.starbucks.delivery.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class DeliveryAddressesResponseVo {

    private List<DeliveryAddressesVo> deliveries;

    @Builder
    private DeliveryAddressesResponseVo(List<DeliveryAddressesVo> deliveries) {
        this.deliveries = deliveries;
    }
}
