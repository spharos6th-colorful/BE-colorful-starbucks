package colorful.starbucks.coupon.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EventCouponUuidResponseVo {

    private String couponUuid;

    @Builder
    private EventCouponUuidResponseVo(String couponUuid) {
        this.couponUuid = couponUuid;
    }
}
