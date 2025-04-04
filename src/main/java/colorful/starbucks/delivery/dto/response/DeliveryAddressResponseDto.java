package colorful.starbucks.delivery.dto.response;

import colorful.starbucks.delivery.domain.DeliveryAddress;
import colorful.starbucks.delivery.vo.response.DeliveryAddressResponseVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class DeliveryAddressResponseDto {

    private String memberAddressUuid;
    private String addressNickname;
    private String receiverName;
    private String address;
    private String zoneCode;
    private boolean isDefaultAddress;
    private String detailAddress;
    private String phoneNumber;

    @Builder
    private DeliveryAddressResponseDto(String memberAddressUuid,
                                       String addressNickname,
                                       String receiverName,
                                       String address,
                                       String zoneCode,
                                       boolean isDefaultAddress,
                                       String detailAddress,
                                       String phoneNumber) {
        this.memberAddressUuid = memberAddressUuid;
        this.addressNickname = addressNickname;
        this.receiverName = receiverName;
        this.address = address;
        this.zoneCode = zoneCode;
        this.isDefaultAddress = isDefaultAddress;
        this.detailAddress = detailAddress;
        this.phoneNumber = phoneNumber;
    }

    public DeliveryAddressResponseVo toVo() {
        return DeliveryAddressResponseVo.builder()
                .memberAddressUuid(memberAddressUuid)
                .addressNickname(addressNickname)
                .receiverName(receiverName)
                .address(address)
                .zoneCode(zoneCode)
                .isDefaultAddress(isDefaultAddress)
                .detailAddress(detailAddress)
                .phoneNumber(phoneNumber)
                .build();
    }

    public static DeliveryAddressResponseDto from(DeliveryAddress deliveryAddress) {
        return DeliveryAddressResponseDto.builder()
                .memberAddressUuid(deliveryAddress.getMemberUuid())
                .addressNickname(deliveryAddress.getAddressNickname())
                .receiverName(deliveryAddress.getReceiverName())
                .address(deliveryAddress.getAddress())
                .zoneCode(deliveryAddress.getZoneCode())
                .isDefaultAddress(deliveryAddress.isDefaultAddress())
                .detailAddress(deliveryAddress.getDetailAddress())
                .phoneNumber(deliveryAddress.getPhoneNumber())
                .build();
    }

}
