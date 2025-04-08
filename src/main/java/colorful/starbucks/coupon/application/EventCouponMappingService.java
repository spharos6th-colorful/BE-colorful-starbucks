package colorful.starbucks.coupon.application;

import colorful.starbucks.coupon.dto.request.EventCouponMappingCreateRequestDto;

public interface EventCouponMappingService {
    void createEventCouponMapping(EventCouponMappingCreateRequestDto eventCouponMappingCreateRequestDto);
}
