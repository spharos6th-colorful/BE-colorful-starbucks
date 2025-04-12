package colorful.starbucks.delivery.dto.response;

import colorful.starbucks.delivery.domain.DeliveryAddress;
import colorful.starbucks.delivery.vo.response.DeliveryAddressListVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class DeliveryAddressListDto {

    private List<DeliveryAddressResponseDto> deliveryAddresses;

    @Builder
    private DeliveryAddressListDto(List<DeliveryAddressResponseDto> deliveryAddresses) {
        this.deliveryAddresses = deliveryAddresses;
    }

    public DeliveryAddressListVo toVo() {
        return DeliveryAddressListVo.builder()
                .deliveryAddresses(
                        deliveryAddresses.stream()
                                .map(DeliveryAddressResponseDto::toVo)
                                .toList())
                .build();
    }

    public static DeliveryAddressListDto from(List<DeliveryAddressResponseDto> deliveryAddresses) {
        return DeliveryAddressListDto.builder()
                .deliveryAddresses(deliveryAddresses)
                .build();
    }

}
