package colorful.starbucks.coupon.presentation;

import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.coupon.application.CouponService;
import colorful.starbucks.coupon.dto.request.CouponCreateRequestDto;
import colorful.starbucks.coupon.vo.request.CouponCreateRequestVo;
import colorful.starbucks.coupon.vo.response.CouponResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/coupons")
@RequiredArgsConstructor
public class CouponController {

    private final CouponService couponService;

    @Operation(
            summary = "쿠폰 등록 API",
            description = "쿠폰을 등록하는 API 입니다.",
            tags = {"COUPON-SERVICE"}
    )
    @PostMapping
    public ApiResponse<Void> createCoupon(@RequestBody CouponCreateRequestVo couponCreateRequestVo) {
        couponService.createCoupon(CouponCreateRequestDto.from(couponCreateRequestVo));
        return ApiResponse.of(
                HttpStatus.CREATED,
                "쿠폰 등록을 완료했습니다.",
                null
        );
    }

    @Operation(
            summary = "쿠폰 조회 API",
            description = "쿠폰 uuid로 쿠폰을 조회하는 API 입니다.",
            tags = {"COUPON-SERVICE"}
    )
    @GetMapping("/{couponUuid}")
    public ApiResponse<CouponResponseVo> getCoupon(@PathVariable String couponUuid) {
        return ApiResponse.ok(
                "쿠폰 조회를 완료했습니다.",
                couponService.getCoupon(couponUuid).toVo()
        );
    }
}
