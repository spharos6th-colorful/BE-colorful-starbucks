package colorful.starbucks.coupon.vo.response;

import colorful.starbucks.coupon.domain.DiscountType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CouponResponseVo {

    private String couponName;
    private String couponDescription;
    private DiscountType discountType;
    private Integer discountValue;
    private String couponImageUrl;
    private Integer maxDiscountAmount;
    private Integer minOrderAmount;
    private LocalDateTime startAt;
    private LocalDateTime expiredAt;

    @Builder
    private CouponResponseVo(String couponName,
                             String couponDescription,
                             DiscountType discountType,
                             Integer discountValue,
                             String couponImageUrl,
                             Integer maxDiscountAmount,
                             Integer minOrderAmount,
                             LocalDateTime startAt,
                             LocalDateTime expiredAt) {
        this.couponName = couponName;
        this.couponDescription = couponDescription;
        this.discountType = discountType;
        this.discountValue = discountValue;
        this.couponImageUrl = couponImageUrl;
        this.maxDiscountAmount = maxDiscountAmount;
        this.minOrderAmount = minOrderAmount;
        this.startAt = startAt;
        this.expiredAt = expiredAt;
    }
}
