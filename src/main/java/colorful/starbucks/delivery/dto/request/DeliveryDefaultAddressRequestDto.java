package colorful.starbucks.delivery.dto.request;

import colorful.starbucks.delivery.vo.request.DeliveryDefaultAddressRequestVo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class DeliveryDefaultAddressRequestDto {

    private String memberAddressUuid;
    private String memberUuid;
    private boolean defaultAddress;

    @Builder
    private DeliveryDefaultAddressRequestDto(String memberAddressUuid,
                                             String memberUuid,
                                             boolean defaultAddress) {
        this.memberAddressUuid = memberAddressUuid;
        this.memberUuid = memberUuid;
        this.defaultAddress = defaultAddress;
    }

    public static DeliveryDefaultAddressRequestDto of(DeliveryDefaultAddressRequestVo deliveryDefaultAddressRequestVo,
                                                      String memberUuid) {
        return DeliveryDefaultAddressRequestDto.builder()
                .memberAddressUuid(deliveryDefaultAddressRequestVo.getMemberAddressUuid())
                .defaultAddress(deliveryDefaultAddressRequestVo.isDefaultAddress())
                .memberUuid(memberUuid)
                .build();
    }

    public static List<DeliveryDefaultAddressRequestDto> of(List<DeliveryDefaultAddressRequestVo> deliveryDefaultAddressRequestVos, String memberUuid) {
        return deliveryDefaultAddressRequestVos.stream()
                .map(vo-> of(vo, memberUuid))
                .toList();
    }
}
