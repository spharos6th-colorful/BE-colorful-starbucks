package colorful.starbucks.coupon.vo.request;

import colorful.starbucks.coupon.domain.DiscountType;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CouponCreateRequestVo {

    private String couponName;
    private String couponDescription;
    private DiscountType discountType;
    private int discountValue;
    private String couponImageUrl;
    private int maxDiscountAmount;
    private int minOrderAmount;
    private int maxIssuanceLimit;
    private LocalDateTime startAt;
    private LocalDateTime expiredAt;
}
