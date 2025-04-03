package colorful.starbucks.delivery.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DeliveryDeleteRequestDto {

    private String memberUuid;
    private String memberAddressUuid;

    @Builder
    private DeliveryDeleteRequestDto(String memberUuid, String memberAddressUuid) {
        this.memberUuid = memberUuid;
        this.memberAddressUuid = memberAddressUuid;
    }

    public static DeliveryDeleteRequestDto of(String memberUuid, String memberAddressUuid) {
        return DeliveryDeleteRequestDto.builder()
                .memberUuid(memberUuid)
                .memberAddressUuid(memberAddressUuid)
                .build();
    }

}
