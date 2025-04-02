package colorful.starbucks.delivery.dto.request;


import colorful.starbucks.delivery.domain.DeliveryAddress;
import colorful.starbucks.delivery.vo.request.DeliveryAddRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class DeliveryAddRequestDto {
    private String memberUuid;
    private String addressNickname;
    private String receiverName;
    private String zoneCode;
    private String address;
    private String detailAddress;
    private String phoneNumber;

    @Builder
    private DeliveryAddRequestDto(String memberUuid, String addressNickname, String receiverName, String zoneCode, String address, String detailAddress, String phoneNumber) {
        this.memberUuid = memberUuid;
        this.addressNickname = addressNickname;
        this.receiverName = receiverName;
        this.zoneCode = zoneCode;
        this.address = address;
        this.detailAddress = detailAddress;
        this.phoneNumber = phoneNumber;
    }

    public static DeliveryAddRequestDto from(DeliveryAddRequestVo deliveryAddRequestVo, String memberUuid) {
        return DeliveryAddRequestDto.builder()
                .memberUuid(memberUuid)
                .addressNickname(deliveryAddRequestVo.getAddressNickname())
                .receiverName(deliveryAddRequestVo.getReceiverName())
                .zoneCode(deliveryAddRequestVo.getZoneCode())
                .address(deliveryAddRequestVo.getAddress())
                .detailAddress(deliveryAddRequestVo.getDetailAddress())
                .phoneNumber(deliveryAddRequestVo.getPhoneNumber())
                .build();
    }
    public DeliveryAddress toEntity(String memberUuid, boolean isDefaultAddress, String memberAddressUuid) {
        return DeliveryAddress.builder()
                .addressNickname(addressNickname)
                .receiverName(receiverName)
                .zoneCode(zoneCode)
                .memberUuid(memberUuid)
                .address(address)
                .detailAddress(detailAddress)
                .phoneNumber(phoneNumber)
                .memberAddressUuid(memberAddressUuid)
                .isDefaultAddress(isDefaultAddress)
                .build();
    }

}
