package colorful.starbucks.delivery.dto.request;

import colorful.starbucks.delivery.vo.request.DeliveryAddressEditRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class DeliveryAddressEditRequestDto {

    private String memberUuid;
    private String memberAddressUuid;
    private String addressNickname;
    private String receiverName;
    private boolean isDefaultAddress;
    private String zoneCode;
    private String address;
    private String detailAddress;
    private String phoneNumber;

    @Builder
    private DeliveryAddressEditRequestDto(String memberUuid, String memberAddressUuid, String addressNickname, String receiverName, boolean isDefaultAddress, String zoneCode, String address, String detailAddress, String phoneNumber) {
        this.memberUuid = memberUuid;
        this.memberAddressUuid = memberAddressUuid;
        this.addressNickname = addressNickname;
        this.receiverName = receiverName;
        this.isDefaultAddress = isDefaultAddress;
        this.zoneCode = zoneCode;
        this.address = address;
        this.detailAddress = detailAddress;
        this.phoneNumber = phoneNumber;
    }

    public static DeliveryAddressEditRequestDto from(DeliveryAddressEditRequestVo deliveryAddressEditRequestVo, String memberUuid, String memberAddressUuid) {
        return DeliveryAddressEditRequestDto.builder()
                .memberUuid(memberUuid)
                .memberAddressUuid(memberAddressUuid)
                .addressNickname(deliveryAddressEditRequestVo.getAddressNickname())
                .receiverName(deliveryAddressEditRequestVo.getReceiverName())
                .isDefaultAddress(deliveryAddressEditRequestVo.isDefaultAddress())
                .zoneCode(deliveryAddressEditRequestVo.getZoneCode())
                .address(deliveryAddressEditRequestVo.getAddress())
                .detailAddress(deliveryAddressEditRequestVo.getDetailAddress())
                .phoneNumber(deliveryAddressEditRequestVo.getPhoneNumber())
                .build();
    }





}
