package colorful.starbucks.coupon.vo.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class EventCouponUuidResponseVo {

    private String couponUuid;

    @Builder
    private EventCouponUuidResponseVo(String couponUuid) {
        this.couponUuid = couponUuid;
    }
}
