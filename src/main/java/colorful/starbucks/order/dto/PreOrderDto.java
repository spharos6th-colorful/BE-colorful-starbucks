package colorful.starbucks.order.dto;

import lombok.Getter;

@Getter
public class PreOrderDto {
    private String memberUuid;

    private String couponUuid;

    private int totalAmount;

    private int discountAmount;

    private String zoneCode;

    private String address;

    private String detailAddress;

    private Boolean isGifted;

    private String buyer;
}
