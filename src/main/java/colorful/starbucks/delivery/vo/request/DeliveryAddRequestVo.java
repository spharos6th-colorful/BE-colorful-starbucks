package colorful.starbucks.delivery.vo.request;

import lombok.Getter;

@Getter
public class DeliveryAddRequestVo {

    private String addressNickname;
    private String receiverName;
    private String zoneCode;
    private String address;
    private String detailAddress;
    private String phoneNumber;

}
