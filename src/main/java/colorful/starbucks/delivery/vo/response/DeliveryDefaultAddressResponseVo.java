package colorful.starbucks.delivery.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeliveryDefaultAddressResponseVo {

    private String addressNickname;
    private String receiverName;
    private Boolean isDefaultAddress;
    private String zoneCode;
    private String address;
    private String detailAddress;
    private String phoneNumber;

    @Builder
    private DeliveryDefaultAddressResponseVo(String addressNickname,
                                             String receiverName,
                                             Boolean isDefaultAddress,
                                             String zoneCode, String address,
                                             String detailAddress,
                                             String phoneNumber) {
        this.addressNickname = addressNickname;
        this.receiverName = receiverName;
        this.isDefaultAddress = isDefaultAddress;
        this.zoneCode = zoneCode;
        this.address = address;
        this.detailAddress = detailAddress;
        this.phoneNumber = phoneNumber;
    }
}
