package colorful.starbucks.delivery.vo.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DeliveryAddressesResponseVo {

    private String memberAddressUuid;
    private String addressNickname;
    private String receiverName;
    private String address;
    private String detailAddress;
    private String phoneNumber;
    private String zoneCode;
    private boolean isDefaultAddress;

    @Builder
    public DeliveryAddressesResponseVo(String memberAddressUuid,
                                       String addressNickname,
                                       String receiverName,
                                       String address,
                                       String detailAddress,
                                       String phoneNumber,
                                       String zoneCode,
                                       boolean isDefaultAddress) {
        this.memberAddressUuid = memberAddressUuid;
        this.addressNickname = addressNickname;
        this.receiverName = receiverName;
        this.address = address;
        this.detailAddress = detailAddress;
        this.phoneNumber = phoneNumber;
        this.zoneCode = zoneCode;
        this.isDefaultAddress = isDefaultAddress;
    }
}
