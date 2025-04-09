package colorful.starbucks.coupon.dto.response;

import colorful.starbucks.coupon.domain.MemberCoupon;
import colorful.starbucks.coupon.vo.response.MemberCouponResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberCouponResponseDto {

    private String couponUuid;

    @Builder
    private MemberCouponResponseDto(String couponUuid) {
        this.couponUuid = couponUuid;
    }

    public static MemberCouponResponseDto from(MemberCoupon memberCoupon) {
        return MemberCouponResponseDto.builder()
                .couponUuid(memberCoupon.getCouponUuid())
                .build();
    }

    public MemberCouponResponseVo toVo() {
        return MemberCouponResponseVo.builder()
                .couponUuid(couponUuid)
                .build();
    }
}
