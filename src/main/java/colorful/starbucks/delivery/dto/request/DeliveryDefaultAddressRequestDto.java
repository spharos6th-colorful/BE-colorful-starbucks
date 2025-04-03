package colorful.starbucks.delivery.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DeliveryDefaultAddressRequestDto {

    private String memberAddressUuid;
    private String memberUuid;

    @Builder
    private DeliveryDefaultAddressRequestDto(String memberAddressUuid, String memberUuid) {
        this.memberAddressUuid = memberAddressUuid;
        this.memberUuid = memberUuid;
    }

    public static DeliveryDefaultAddressRequestDto of(String memberAddressUuid, String memberUuid) {
        return DeliveryDefaultAddressRequestDto.builder()
                .memberAddressUuid(memberAddressUuid)
                .memberUuid(memberUuid).build();
    }
}
