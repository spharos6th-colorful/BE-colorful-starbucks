package colorful.starbucks.delivery.dto.request;

import colorful.starbucks.delivery.vo.request.DeliveryDeleteRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class DeliveryDeleteRequestDto {

    private String memberAddressUuid;

    @Builder
    public DeliveryDeleteRequestDto(String memberAddressUuid) {
        this.memberAddressUuid = memberAddressUuid;
    }

    public static DeliveryDeleteRequestDto from(DeliveryDeleteRequestVo deliveryDeleteRequestVo) {
        return DeliveryDeleteRequestDto.builder()
                .memberAddressUuid(deliveryDeleteRequestVo.getMemberAddressUuid())
                .build();
    }

}
