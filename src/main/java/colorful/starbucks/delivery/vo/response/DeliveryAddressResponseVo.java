package colorful.starbucks.delivery.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeliveryAddressResponseVo {

    private String memberAddressUuid;
    private String addressNickname;
    private String receiverName;
    private String address;
    private String zoneCode;
    private Boolean isDefaultAddress;
    private String detailAddress;
    private String phoneNumber;

    @Builder
    private DeliveryAddressResponseVo(String memberAddressUuid,
                                      String addressNickname,
                                      String receiverName,
                                      String address,
                                      String zoneCode,
                                      Boolean isDefaultAddress,
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
