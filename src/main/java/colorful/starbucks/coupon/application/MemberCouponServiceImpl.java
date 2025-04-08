package colorful.starbucks.coupon.application;

import colorful.starbucks.common.aop.DistributedLock;
import colorful.starbucks.common.exception.BaseException;
import colorful.starbucks.common.response.ResponseStatus;
import colorful.starbucks.coupon.domain.Coupon;
import colorful.starbucks.coupon.dto.request.MemberCouponCreateRequestDto;
import colorful.starbucks.coupon.dto.response.MemberCouponResponseDto;
import colorful.starbucks.coupon.infrastructure.CouponRepository;
import colorful.starbucks.coupon.infrastructure.MemberCouponRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberCouponServiceImpl implements MemberCouponService {

    private final MemberCouponRepository memberCouponRepository;
    private final CouponRepository couponRepository;

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @DistributedLock(key = "#memberCouponCreateRequestDto.getCouponUuid()")
    @Override
    public void createMemberCoupon(MemberCouponCreateRequestDto memberCouponCreateRequestDto) {

        Coupon coupon = couponRepository.findByCouponUuid(memberCouponCreateRequestDto.getCouponUuid())
                .orElseThrow(() -> new BaseException(ResponseStatus.RESOURCE_NOT_FOUND));
        coupon.issue();
        memberCouponRepository.save(memberCouponCreateRequestDto.toEntity());
    }

    @Override
    public MemberCouponResponseDto getMemberCoupons(String memberUuid) {
        return null;
    }
}
