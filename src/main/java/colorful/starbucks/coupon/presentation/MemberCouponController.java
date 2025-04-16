package colorful.starbucks.coupon.presentation;

import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.common.util.CursorPage;
import colorful.starbucks.coupon.application.MemberCouponService;
import colorful.starbucks.coupon.dto.request.MemberCouponCreateRequestDto;
import colorful.starbucks.coupon.dto.request.MemberCouponRequestDto;
import colorful.starbucks.coupon.dto.response.MemberCouponResponseDto;
import colorful.starbucks.coupon.vo.request.MemberCouponCreateRequestVo;
import colorful.starbucks.coupon.vo.request.MemberCouponRequestVo;
import colorful.starbucks.coupon.vo.response.MemberCouponResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/member-coupons")
@RequiredArgsConstructor
public class MemberCouponController {

    private final MemberCouponService memberCouponService;

    @Operation(
            summary = "사용자 쿠폰 발급 API",
            description = "사용자에게 쿠폰을 발급하는 API 입니다.",
            tags = {"COUPON-SERVICE"}
    )
    @PostMapping
    public ApiResponse<Void> createMemberCoupon(Authentication authentication,
                                                @RequestBody MemberCouponCreateRequestVo memberCouponCreateRequestVo) {
        memberCouponService.createMemberCoupon(
                MemberCouponCreateRequestDto.of(authentication.getName(), memberCouponCreateRequestVo)
        );
        return ApiResponse.ok(
                "쿠폰 발급을 완료했습니다.",
                null
        );
    }

    @Operation(
            summary = "사용자 쿠폰 조회 API",
            description = "사용자가 보유한 쿠폰을 조회하는 API 입니다.",
            tags = {"COUPON-SERVICE"}
    )
    @GetMapping
    public ApiResponse<CursorPage<MemberCouponResponseVo>> getMemberCoupons(Authentication authentication,
                                                                            @ModelAttribute MemberCouponRequestVo memberCouponRequestVo) {
        return ApiResponse.ok(
                "사용자 쿠폰 조회를 완료했습니다.",
                memberCouponService.getMemberCoupons(MemberCouponRequestDto.of(authentication.getName(), memberCouponRequestVo))
                        .map(MemberCouponResponseDto::toVo)
        );
    }
}
