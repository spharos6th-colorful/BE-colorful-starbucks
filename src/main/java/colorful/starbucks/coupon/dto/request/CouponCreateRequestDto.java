package colorful.starbucks.coupon.dto.request;

import colorful.starbucks.coupon.domain.Coupon;
import colorful.starbucks.coupon.domain.DiscountType;
import colorful.starbucks.coupon.generator.CouponUuidGenerator;
import colorful.starbucks.coupon.vo.request.CouponCreateRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CouponCreateRequestDto {

    private String couponUuid;
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

    @Builder
    private CouponCreateRequestDto(String couponUuid,
                                   String couponName,
                                   String couponDescription,
                                   DiscountType discountType,
                                   Integer discountValue,
                                   String couponImageUrl,
                                   Integer maxDiscountAmount,
                                   Integer minOrderAmount,
                                   Integer maxIssuanceLimit,
                                   LocalDateTime startAt,
                                   LocalDateTime expiredAt) {
        this.couponUuid = couponUuid;
        this.couponName = couponName;
        this.couponDescription = couponDescription;
        this.discountType = discountType;
        this.discountValue = discountValue;
        this.couponImageUrl = couponImageUrl;
        this.maxDiscountAmount = maxDiscountAmount;
        this.minOrderAmount = minOrderAmount;
        this.maxIssuanceLimit = maxIssuanceLimit;
        this.startAt = startAt;
        this.expiredAt = expiredAt;
    }

    public static CouponCreateRequestDto from(CouponCreateRequestVo couponCreateRequestVo) {
        return CouponCreateRequestDto.builder()
                .couponUuid(CouponUuidGenerator.generate())
                .couponName(couponCreateRequestVo.getCouponName())
                .couponDescription(couponCreateRequestVo.getCouponDescription())
                .discountType(couponCreateRequestVo.getDiscountType())
                .discountValue(couponCreateRequestVo.getDiscountValue())
                .couponImageUrl(couponCreateRequestVo.getCouponImageUrl())
                .maxDiscountAmount(couponCreateRequestVo.getMaxDiscountAmount())
                .minOrderAmount(couponCreateRequestVo.getMinOrderAmount())
                .maxIssuanceLimit(couponCreateRequestVo.getMaxIssuanceLimit())
                .startAt(couponCreateRequestVo.getStartAt())
                .expiredAt(couponCreateRequestVo.getExpiredAt())
                .build();
    }

    public Coupon toEntity() {
        return Coupon.builder()
                .couponUuid(couponUuid)
                .couponName(couponName)
                .couponDescription(couponDescription)
                .discountType(discountType)
                .discountValue(discountValue)
                .couponImageUrl(couponImageUrl)
                .maxDiscountAmount(maxDiscountAmount)
                .minOrderAmount(minOrderAmount)
                .maxIssuanceLimit(maxIssuanceLimit)
                .currentIssuanceCount(0)
                .startAt(startAt)
                .expiredAt(expiredAt)
                .build();
    }
}
