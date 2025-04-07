package colorful.starbucks.coupon.application;

import colorful.starbucks.coupon.dto.request.CouponCreateRequestDto;

public interface CouponService {

    void createCoupon(CouponCreateRequestDto couponCreateRequestDto);
}
