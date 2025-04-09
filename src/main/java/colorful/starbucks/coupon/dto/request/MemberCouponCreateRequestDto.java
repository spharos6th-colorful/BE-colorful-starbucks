package colorful.starbucks.coupon.dto.request;

import colorful.starbucks.coupon.domain.MemberCoupon;
import colorful.starbucks.coupon.vo.request.MemberCouponCreateRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MemberCouponCreateRequestDto {

    private String memberUuid;
    private String couponUuid;
    private Boolean isUsed;
    private LocalDateTime usedAt;
    private String usedOrderCode;

    @Builder
    private MemberCouponCreateRequestDto(String memberUuid,
                                         String couponUuid,
                                         boolean isUsed,
                                         LocalDateTime usedAt,
                                         String usedOrderCode) {
        this.memberUuid = memberUuid;
        this.couponUuid = couponUuid;
        this.isUsed = isUsed;
        this.usedAt = usedAt;
        this.usedOrderCode = usedOrderCode;
    }

    public static MemberCouponCreateRequestDto of(String memberUuid, MemberCouponCreateRequestVo memberCouponCreateRequestVo) {
        return MemberCouponCreateRequestDto.builder()
                .memberUuid(memberUuid)
                .couponUuid(memberCouponCreateRequestVo.getCouponUuid())
                .isUsed(false)
                .usedAt(null)
                .usedOrderCode(null)
                .build();
    }

    public MemberCoupon toEntity() {
        return MemberCoupon.builder()
                .memberUuid(memberUuid)
                .couponUuid(couponUuid)
                .isUsed(isUsed)
                .usedAt(usedAt)
                .usedOrderCode(usedOrderCode)
                .build();
    }
}
