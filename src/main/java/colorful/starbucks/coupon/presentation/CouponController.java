package colorful.starbucks.coupon.presentation;

import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.coupon.application.CouponService;
import colorful.starbucks.coupon.dto.request.CouponCreateRequestDto;
import colorful.starbucks.coupon.vo.request.CouponCreateRequestVo;
import colorful.starbucks.coupon.vo.response.CouponResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/coupons")
@RequiredArgsConstructor
public class CouponController {

    private final CouponService couponService;

    @PostMapping
    public ApiResponse<Void> createCoupon(@RequestBody CouponCreateRequestVo couponCreateRequestVo) {
        couponService.createCoupon(CouponCreateRequestDto.from(couponCreateRequestVo));
        return ApiResponse.of(
                HttpStatus.CREATED,
                "쿠폰 등록을 완료했습니다.",
                null
        );
    }

    @GetMapping("/{couponUuid}")
    public ApiResponse<CouponResponseVo> getCoupon(@PathVariable String couponUuid) {
        return ApiResponse.ok(
                "쿠폰 조회를 완료했습니다.",
                couponService.getCoupon(couponUuid).toVo()
        );
    }
}
