package colorful.starbucks.delivery.dto.response;

import colorful.starbucks.delivery.domain.DeliveryAddress;
import colorful.starbucks.delivery.vo.response.DeliveryAddressesResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeliveryAddressesResponseDto {
    private String memberAddressUuid;
    private String addressNickname;
    private String receiverName;
    private String address;
    private String detailAddress;
    private String phoneNumber;
    private String zoneCode;
    private Boolean isDefaultAddress;

    @Builder
    private DeliveryAddressesResponseDto(String memberAddressUuid,
                                         String addressNickname,
                                         String receiverName,
                                         String address,
                                         String detailAddress,
                                         String phoneNumber,
                                         String zoneCode,
                                         Boolean isDefaultAddress) {
        this.memberAddressUuid = memberAddressUuid;
        this.addressNickname = addressNickname;
        this.receiverName = receiverName;
        this.address = address;
        this.detailAddress = detailAddress;
        this.phoneNumber = phoneNumber;
        this.zoneCode = zoneCode;
        this.isDefaultAddress = isDefaultAddress;
    }

    public DeliveryAddressesResponseVo toVo() {
        return DeliveryAddressesResponseVo.builder()
                .memberAddressUuid(memberAddressUuid)
                .addressNickname(addressNickname)
                .receiverName(receiverName)
                .address(address)
                .detailAddress(detailAddress)
                .phoneNumber(phoneNumber)
                .zoneCode(zoneCode)
                .isDefaultAddress(isDefaultAddress)
                .build();
    }

    public static DeliveryAddressesResponseDto from(DeliveryAddress deliveryAddress) {
        return DeliveryAddressesResponseDto.builder()
                .memberAddressUuid(deliveryAddress.getMemberUuid())
                .addressNickname(deliveryAddress.getAddressNickname())
                .receiverName(deliveryAddress.getReceiverName())
                .address(deliveryAddress.getAddress())
                .detailAddress(deliveryAddress.getDetailAddress())
                .phoneNumber(deliveryAddress.getPhoneNumber())
                .zoneCode(deliveryAddress.getZoneCode())
                .isDefaultAddress(deliveryAddress.isDefaultAddress())
                .build();
    }

}
