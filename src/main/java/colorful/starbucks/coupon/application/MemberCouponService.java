package colorful.starbucks.coupon.application;

import colorful.starbucks.common.util.CursorPage;
import colorful.starbucks.coupon.dto.request.MemberCouponCreateRequestDto;
import colorful.starbucks.coupon.dto.request.MemberCouponRequestDto;
import colorful.starbucks.coupon.dto.response.MemberCouponResponseDto;

import java.time.LocalDateTime;

public interface MemberCouponService {

    void createMemberCoupon(MemberCouponCreateRequestDto memberCouponCreateRequestDto);

    CursorPage<MemberCouponResponseDto> getMemberCoupons(MemberCouponRequestDto memberCouponRequestDto);

    void useCoupon(String couponUuid, String memberUuid, LocalDateTime usedAt);
}
