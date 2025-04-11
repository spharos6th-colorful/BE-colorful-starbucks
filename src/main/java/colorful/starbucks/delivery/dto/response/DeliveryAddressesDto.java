package colorful.starbucks.delivery.dto.response;

import colorful.starbucks.delivery.domain.DeliveryAddress;
import colorful.starbucks.delivery.vo.response.DeliveryAddressesVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeliveryAddressesDto {
    private String memberAddressUuid;
    private String addressNickname;
    private String receiverName;
    private String address;
    private String detailAddress;
    private String phoneNumber;
    private String zoneCode;
    private Boolean isDefaultAddress;

    @Builder
    private DeliveryAddressesDto(String memberAddressUuid,
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

    public DeliveryAddressesVo toVo() {
        return DeliveryAddressesVo.builder()
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

    public static DeliveryAddressesDto from(DeliveryAddress deliveryAddress) {
        return DeliveryAddressesDto.builder()
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
