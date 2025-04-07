package colorful.starbucks.coupon.presentation;

import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.coupon.application.MemberCouponService;
import colorful.starbucks.coupon.dto.request.MemberCouponCreateRequestDto;
import colorful.starbucks.coupon.vo.request.MemberCouponCreateRequestVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/member-coupons")
@RequiredArgsConstructor
public class MemberCouponController {

    private final MemberCouponService memberCouponService;

    @PostMapping
    public ApiResponse<Void> createMemberCoupon(Authentication authentication,
                                                @RequestBody MemberCouponCreateRequestVo memberCouponCreateRequestVo) {
        memberCouponService.createMemberCoupon(
                MemberCouponCreateRequestDto.from(authentication.getName(), memberCouponCreateRequestVo)
        );
        return ApiResponse.ok(
                "쿠폰 발급을 완료했습니다.",
                null
        );
    }
}
