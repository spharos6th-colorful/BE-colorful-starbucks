package colorful.starbucks.order.dto;

import colorful.starbucks.order.vo.OrderAddressVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderAddressDto {
    private String zoneCode;
    private String address;
    private String detailAddress;

    @Builder
    private OrderAddressDto(String zoneCode,
                           String address,
                           String detailAddress) {
        this.zoneCode = zoneCode;
        this.address = address;
        this.detailAddress = detailAddress;
    }
    public OrderAddressVo toVo() {
        return OrderAddressVo.builder()
                .zoneCode(zoneCode)
                .address(address)
                .detailAddress(detailAddress)
                .build();
    }
}
