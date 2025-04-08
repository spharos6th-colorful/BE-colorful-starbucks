package colorful.starbucks.coupon.infrastructure;

import colorful.starbucks.common.util.CursorPage;
import colorful.starbucks.coupon.domain.MemberCoupon;
import colorful.starbucks.coupon.dto.request.MemberCouponRequestDto;

public interface MemberCouponRepositoryCustom {

    CursorPage<MemberCoupon> getAllCouponsByMemberUuid(MemberCouponRequestDto memberCouponRequestDto);
}
