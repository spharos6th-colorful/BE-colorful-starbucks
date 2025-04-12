package colorful.starbucks.coupon.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberCouponResponseVo {

    private String couponUuid;

    @Builder
    private MemberCouponResponseVo(String couponUuid) {
        this.couponUuid = couponUuid;
    }
}
