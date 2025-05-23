package colorful.starbucks.delivery.dto.response;

import colorful.starbucks.delivery.domain.DeliveryAddress;
import colorful.starbucks.delivery.vo.response.DeliveryDefaultAddressResponseVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class DeliveryDefaultAddressResponseDto {

    private String addressNickname;
    private String memberAddressUuid;
    private String receiverName;
    private Boolean isDefaultAddress;
    private String zoneCode;
    private String address;
    private String detailAddress;
    private String phoneNumber;

    @Builder
    private DeliveryDefaultAddressResponseDto(String addressNickname,
                                              String memberAddressUuid,
                                              String receiverName,
                                              Boolean isDefaultAddress,
                                              String zoneCode,
                                              String address,
                                              String detailAddress,
                                              String phoneNumber) {
        this.addressNickname = addressNickname;
        this.memberAddressUuid = memberAddressUuid;
        this.receiverName = receiverName;
        this.isDefaultAddress = isDefaultAddress;
        this.zoneCode = zoneCode;
        this.address = address;
        this.detailAddress = detailAddress;
        this.phoneNumber = phoneNumber;
    }

    public DeliveryDefaultAddressResponseVo toVo() {
        return DeliveryDefaultAddressResponseVo.builder()
                .addressNickname(addressNickname)
                .memberAddressUuid(memberAddressUuid)
                .receiverName(receiverName)
                .isDefaultAddress(isDefaultAddress)
                .zoneCode(zoneCode)
                .address(address)
                .detailAddress(detailAddress)
                .phoneNumber(phoneNumber)
                .build();
    }

    public static DeliveryDefaultAddressResponseDto from(DeliveryAddress deliveryAddress) {
        return DeliveryDefaultAddressResponseDto.builder()
                .addressNickname(deliveryAddress.getAddressNickname())
                .memberAddressUuid(deliveryAddress.getMemberAddressUuid())
                .receiverName(deliveryAddress.getReceiverName())
                .isDefaultAddress(deliveryAddress.isDefaultAddress())
                .zoneCode(deliveryAddress.getZoneCode())
                .address(deliveryAddress.getAddress())
                .detailAddress(deliveryAddress.getDetailAddress())
                .phoneNumber(deliveryAddress.getPhoneNumber())
                .build();

    }
}
