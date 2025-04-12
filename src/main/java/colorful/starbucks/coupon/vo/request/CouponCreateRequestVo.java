package colorful.starbucks.coupon.vo.request;

import colorful.starbucks.coupon.domain.DiscountType;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CouponCreateRequestVo {

    private String couponName;
    private String couponDescription;
    private DiscountType discountType;
    private Integer discountValue;
    private String couponImageUrl;
    private Integer maxDiscountAmount;
    private Integer minOrderAmount;
    private Integer maxIssuanceLimit;
    private LocalDateTime startAt;
    private LocalDateTime expiredAt;
}
