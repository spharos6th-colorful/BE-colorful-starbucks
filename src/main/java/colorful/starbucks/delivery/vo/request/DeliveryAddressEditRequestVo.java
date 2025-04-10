package colorful.starbucks.delivery.vo.request;

import lombok.Getter;

@Getter
public class DeliveryAddressEditRequestVo {

    private String addressNickname;
    private String receiverName;
    private Boolean defaultAddress;
    private String zoneCode;
    private String address;
    private String detailAddress;
    private String phoneNumber;

}