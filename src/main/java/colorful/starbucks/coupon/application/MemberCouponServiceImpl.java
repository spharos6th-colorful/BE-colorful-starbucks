package colorful.starbucks.coupon.application;

import colorful.starbucks.common.exception.BaseException;
import colorful.starbucks.common.response.ResponseStatus;
import colorful.starbucks.coupon.domain.Coupon;
import colorful.starbucks.coupon.dto.request.MemberCouponCreateRequestDto;
import colorful.starbucks.coupon.infrastructure.CouponRepository;
import colorful.starbucks.coupon.infrastructure.MemberCouponRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberCouponServiceImpl implements MemberCouponService {

    private final MemberCouponRepository memberCouponRepository;
    private final CouponRepository couponRepository;

    @Transactional
    @Override
    public void createMemberCoupon(MemberCouponCreateRequestDto memberCouponCreateRequestDto) {
        
        // 쿠폰 uuid로 쿠폰 수량 확인
        Coupon coupon = couponRepository.findByCouponUuid(memberCouponCreateRequestDto.getCouponUuid())
                .orElseThrow(() -> new BaseException(ResponseStatus.RESOURCE_NOT_FOUND));

        coupon.issue();

        // 회원 쿠폰 발급
        memberCouponRepository.save(memberCouponCreateRequestDto.toEntity());
    }
}
