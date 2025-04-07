package colorful.starbucks.coupon.application;

import colorful.starbucks.coupon.dto.request.CouponCreateRequestDto;
import colorful.starbucks.coupon.dto.response.CouponResponseDto;

public interface CouponService {

    void createCoupon(CouponCreateRequestDto couponCreateRequestDto);

    CouponResponseDto getCoupon(String couponUuid);
}
