package colorful.starbucks.coupon.dto.request;

import colorful.starbucks.coupon.domain.EventCouponMapping;
import colorful.starbucks.coupon.vo.request.EventCouponMappingCreateRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class EventCouponMappingCreateRequestDto {

    private String eventUuid;
    private String couponUuid;

    @Builder
    private EventCouponMappingCreateRequestDto(String eventUuid, String couponUuid) {
        this.eventUuid = eventUuid;
        this.couponUuid = couponUuid;
    }

    public static EventCouponMappingCreateRequestDto from(EventCouponMappingCreateRequestVo eventCouponMappingCreateRequestVo) {
        return EventCouponMappingCreateRequestDto.builder()
                .eventUuid(eventCouponMappingCreateRequestVo.getEventUuid())
                .couponUuid(eventCouponMappingCreateRequestVo.getCouponUuid())
                .build();
    }

    public EventCouponMapping toEntity() {
        return EventCouponMapping.builder()
                .eventUuid(eventUuid)
                .couponUuid(couponUuid)
                .build();
    }
}
