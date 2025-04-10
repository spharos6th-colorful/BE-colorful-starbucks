package colorful.starbucks.delivery.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeliveryAddressRequestDto {
    private String memberUuid;
    private String memberAddressUuid;

    @Builder
    private DeliveryAddressRequestDto(String memberUuid, String memberAddressUuid) {
        this.memberUuid = memberUuid;
        this.memberAddressUuid = memberAddressUuid;
    }

    public static DeliveryAddressRequestDto of(String memberUuid, String memberAddressUuid) {
        return DeliveryAddressRequestDto.builder()
                .memberUuid(memberUuid)
                .memberAddressUuid(memberAddressUuid)
                .build();
    }
}
