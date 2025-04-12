package colorful.starbucks.coupon.application;

import colorful.starbucks.coupon.dto.request.EventCouponMappingCreateRequestDto;
import colorful.starbucks.coupon.dto.request.EventCouponUuidRequestDto;
import colorful.starbucks.coupon.dto.response.EventCouponUuidResponseDto;
import org.springframework.data.domain.Page;

public interface EventCouponMappingService {
    void createEventCouponMapping(EventCouponMappingCreateRequestDto eventCouponMappingCreateRequestDto);

    Page<EventCouponUuidResponseDto> getEventCouponMappingList(EventCouponUuidRequestDto eventCouponUuidRequestDto);
}
