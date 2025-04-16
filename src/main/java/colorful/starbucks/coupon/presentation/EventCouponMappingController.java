package colorful.starbucks.coupon.presentation;

import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.common.util.PageResponse;
import colorful.starbucks.coupon.application.EventCouponMappingService;
import colorful.starbucks.coupon.dto.request.EventCouponMappingCreateRequestDto;
import colorful.starbucks.coupon.dto.request.EventCouponUuidRequestDto;
import colorful.starbucks.coupon.dto.response.EventCouponUuidResponseDto;
import colorful.starbucks.coupon.vo.request.EventCouponMappingCreateRequestVo;
import colorful.starbucks.coupon.vo.response.EventCouponUuidResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/event-coupons")
@RequiredArgsConstructor
public class EventCouponMappingController {

    private final EventCouponMappingService eventCouponMappingService;

    @Operation(
            summary = "이벤트 쿠폰 생성 API",
            description = "이벤트와 쿠폰을 생성하는 API 입니다.",
            tags = {"COUPON-SERVICE"}
    )
    @PostMapping
    public ApiResponse<Void> createEventCouponMapping(@RequestBody EventCouponMappingCreateRequestVo eventCouponMappingCreateRequestVo) {
        eventCouponMappingService.createEventCouponMapping(EventCouponMappingCreateRequestDto.from(eventCouponMappingCreateRequestVo));
        return ApiResponse.of(
                HttpStatus.CREATED,
                "이벤트 쿠폰을 생성했습니다.",
                null
        );
    }

    @Operation(
            summary = "이벤트 쿠폰 매핑 목록 조회 API",
            description = "이벤트 uuid로 해당 이벤트가 가진 쿠폰 목록을 조회하는 API 입니다.",
            tags = {"COUPON-SERVICE"}
    )
    @GetMapping("/{eventUuid}")
    public ApiResponse<PageResponse<EventCouponUuidResponseVo>> getEventCouponList(@PathVariable String eventUuid,
                                                                                   @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ApiResponse.ok(
                "이벤트 쿠폰 매핑 목록 조회에 성공했습니다.",
                PageResponse.from(
                        eventCouponMappingService.getEventCouponMappingList(EventCouponUuidRequestDto.of(eventUuid, pageable))
                                .map(EventCouponUuidResponseDto::toVo)
                )
        );
    }
}
