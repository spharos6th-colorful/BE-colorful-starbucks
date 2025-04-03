package colorful.starbucks.delivery.vo.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DeliveryAddressResponseVo {

    private String memberAddressUuid;
    private String addressNickname;
    private String receiverName;
    private String address;
    private String zoneCode;
    private boolean isDefaultAddress;
    private String detailAddress;
    private String phoneNumber;

    @Builder
    public DeliveryAddressResponseVo(String memberAddressUuid,
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
}
