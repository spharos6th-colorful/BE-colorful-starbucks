package colorful.starbucks.coupon.dto.response;

import colorful.starbucks.coupon.domain.EventCouponMapping;
import colorful.starbucks.coupon.vo.response.EventCouponUuidResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EventCouponUuidResponseDto {

    private String couponUuid;

    @Builder
    private EventCouponUuidResponseDto(String couponUuid) {
        this.couponUuid = couponUuid;
    }

    public EventCouponUuidResponseVo toVo() {
        return EventCouponUuidResponseVo.builder()
                .couponUuid(couponUuid)
                .build();
    }

    public static EventCouponUuidResponseDto from(EventCouponMapping eventCouponMapping) {
        return EventCouponUuidResponseDto.builder()
                .couponUuid(eventCouponMapping.getCouponUuid())
                .build();
    }
}
