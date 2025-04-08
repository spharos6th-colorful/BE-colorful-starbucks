package colorful.starbucks.coupon.application;

import colorful.starbucks.coupon.dto.request.MemberCouponCreateRequestDto;

public interface MemberCouponService {

    void createMemberCoupon(MemberCouponCreateRequestDto memberCouponCreateRequestDto);
}
