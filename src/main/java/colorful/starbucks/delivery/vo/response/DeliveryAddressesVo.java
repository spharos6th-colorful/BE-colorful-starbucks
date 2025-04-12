package colorful.starbucks.delivery.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeliveryAddressesVo {

    private String memberAddressUuid;
    private String addressNickname;
    private String receiverName;
    private String address;
    private String detailAddress;
    private String phoneNumber;
    private String zoneCode;
    private Boolean isDefaultAddress;

    @Builder
    private DeliveryAddressesVo(String memberAddressUuid,
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
}
