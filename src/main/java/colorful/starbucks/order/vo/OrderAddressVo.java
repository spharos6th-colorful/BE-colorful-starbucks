package colorful.starbucks.order.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderAddressVo {

    private String zoneCode;
    private String address;
    private String detailAddress;

    @Builder
    private OrderAddressVo(String zoneCode,
                           String address,
                           String detailAddress) {
        this.zoneCode = zoneCode;
        this.address = address;
        this.detailAddress = detailAddress;
    }

}
