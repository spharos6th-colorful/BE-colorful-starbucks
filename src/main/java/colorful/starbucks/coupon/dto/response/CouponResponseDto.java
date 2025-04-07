package colorful.starbucks.coupon.dto.response;

import colorful.starbucks.coupon.domain.Coupon;
import colorful.starbucks.coupon.domain.DiscountType;
import colorful.starbucks.coupon.vo.response.CouponResponseVo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CouponResponseDto {

    private String couponName;
    private String couponDescription;
    private DiscountType discountType;
    private int discountValue;
    private String couponImageUrl;
    private int maxDiscountAmount;
    private int minOrderAmount;
    private LocalDateTime startAt;
    private LocalDateTime expiredAt;

    @Builder
    private CouponResponseDto(String couponName,
                              String couponDescription,
                              DiscountType discountType,
                              int discountValue,
                              String couponImageUrl,
                              int maxDiscountAmount,
                              int minOrderAmount,
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

    public static CouponResponseDto from(Coupon coupon) {
        return CouponResponseDto.builder()
                .couponName(coupon.getCouponName())
                .couponDescription(coupon.getCouponDescription())
                .discountType(coupon.getDiscountType())
                .discountValue(coupon.getDiscountValue())
                .couponImageUrl(coupon.getCouponImageUrl())
                .maxDiscountAmount(coupon.getMaxDiscountAmount())
                .minOrderAmount(coupon.getMinOrderAmount())
                .startAt(coupon.getStartAt())
                .expiredAt(coupon.getExpiredAt())
                .build();
    }

    public CouponResponseVo toVo() {
        return CouponResponseVo.builder()
                .couponName(couponName)
                .couponDescription(couponDescription)
                .discountType(discountType)
                .discountValue(discountValue)
                .couponImageUrl(couponImageUrl)
                .maxDiscountAmount(maxDiscountAmount)
                .minOrderAmount(minOrderAmount)
                .startAt(startAt)
                .expiredAt(expiredAt)
                .build();
    }
}
