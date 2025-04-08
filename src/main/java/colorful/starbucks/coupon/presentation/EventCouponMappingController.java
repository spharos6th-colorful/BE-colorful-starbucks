package colorful.starbucks.coupon.presentation;

import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.coupon.application.EventCouponMappingService;
import colorful.starbucks.coupon.dto.request.EventCouponMappingCreateRequestDto;
import colorful.starbucks.coupon.vo.request.EventCouponMappingCreateRequestVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/event-coupons")
@RequiredArgsConstructor
public class EventCouponMappingController {

    private final EventCouponMappingService eventCouponMappingService;

    @PostMapping
    public ApiResponse<Void> createEventCouponMapping(@RequestBody EventCouponMappingCreateRequestVo eventCouponMappingCreateRequestVo) {
        eventCouponMappingService.createEventCouponMapping(EventCouponMappingCreateRequestDto.from(eventCouponMappingCreateRequestVo));
        return ApiResponse.ok(
                "이벤트 쿠폰 매핑을 완료했습니다.",
                null
        );
    }
}
