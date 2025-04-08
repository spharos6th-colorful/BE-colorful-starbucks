package colorful.starbucks.coupon.application;

import colorful.starbucks.coupon.dto.request.MemberCouponCreateRequestDto;
import colorful.starbucks.coupon.dto.response.MemberCouponResponseDto;

public interface MemberCouponService {

    void createMemberCoupon(MemberCouponCreateRequestDto memberCouponCreateRequestDto);

    MemberCouponResponseDto getMemberCoupons(String memberUuid);
}
