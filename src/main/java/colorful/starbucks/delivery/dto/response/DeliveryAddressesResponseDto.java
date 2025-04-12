package colorful.starbucks.delivery.dto.response;

import colorful.starbucks.delivery.vo.response.DeliveryAddressesResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class DeliveryAddressesResponseDto {

    private List<DeliveryAddressesDto> deliveries;

    @Builder
    private DeliveryAddressesResponseDto(List<DeliveryAddressesDto> deliveries) {
        this.deliveries = deliveries;
    }

    public static DeliveryAddressesResponseDto from(List<DeliveryAddressesDto> deliveries) {
        return DeliveryAddressesResponseDto.builder()
                .deliveries(deliveries)
                .build();
    }

    public DeliveryAddressesResponseVo toVo(){
        return DeliveryAddressesResponseVo.builder()
                .deliveries(
                        deliveries.stream()
                                .map(DeliveryAddressesDto::toVo)
                                .toList()
                )
                .build();
    }

}
