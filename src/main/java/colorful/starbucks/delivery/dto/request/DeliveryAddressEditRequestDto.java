package colorful.starbucks.delivery.dto.request;

import colorful.starbucks.delivery.vo.request.DeliveryAddressEditRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeliveryAddressEditRequestDto {

    private String memberUuid;
    private String memberAddressUuid;
    private String addressNickname;
    private String receiverName;
    private Boolean defaultAddress;
    private String zoneCode;
    private String address;
    private String detailAddress;
    private String phoneNumber;

    @Builder
    private DeliveryAddressEditRequestDto(String memberUuid,
                                          String memberAddressUuid,
                                          String addressNickname,
                                          String receiverName,
                                          Boolean defaultAddress,
                                          String zoneCode,
                                          String address,
                                          String detailAddress,
                                          String phoneNumber) {
        this.memberUuid = memberUuid;
        this.memberAddressUuid = memberAddressUuid;
        this.addressNickname = addressNickname;
        this.receiverName = receiverName;
        this.defaultAddress = defaultAddress;
        this.zoneCode = zoneCode;
        this.address = address;
        this.detailAddress = detailAddress;
        this.phoneNumber = phoneNumber;
    }

    public static DeliveryAddressEditRequestDto of(DeliveryAddressEditRequestVo deliveryAddressEditRequestVo,
                                                     String memberUuid,
                                                     String memberAddressUuid) {

        return DeliveryAddressEditRequestDto.builder()
                .memberUuid(memberUuid)
                .memberAddressUuid(memberAddressUuid)
                .addressNickname(deliveryAddressEditRequestVo.getAddressNickname())
                .receiverName(deliveryAddressEditRequestVo.getReceiverName())
                .defaultAddress(deliveryAddressEditRequestVo.getDefaultAddress())
                .zoneCode(deliveryAddressEditRequestVo.getZoneCode())
                .address(deliveryAddressEditRequestVo.getAddress())
                .detailAddress(deliveryAddressEditRequestVo.getDetailAddress())
                .phoneNumber(deliveryAddressEditRequestVo.getPhoneNumber())
                .build();

    }


}
