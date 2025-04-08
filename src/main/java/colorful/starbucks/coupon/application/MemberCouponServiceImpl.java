package colorful.starbucks.coupon.application;

import colorful.starbucks.common.aop.DistributedLock;
import colorful.starbucks.common.exception.BaseException;
import colorful.starbucks.common.response.ResponseStatus;
import colorful.starbucks.common.util.CursorPage;
import colorful.starbucks.coupon.domain.Coupon;
import colorful.starbucks.coupon.dto.request.MemberCouponCreateRequestDto;
import colorful.starbucks.coupon.dto.request.MemberCouponRequestDto;
import colorful.starbucks.coupon.dto.response.MemberCouponResponseDto;
import colorful.starbucks.coupon.infrastructure.CouponRepository;
import colorful.starbucks.coupon.infrastructure.MemberCouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberCouponServiceImpl implements MemberCouponService {

    private final MemberCouponRepository memberCouponRepository;
    private final CouponRepository couponRepository;

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @DistributedLock(key = "#memberCouponCreateRequestDto.getCouponUuid()")
    @Override
    public void createMemberCoupon(MemberCouponCreateRequestDto memberCouponCreateRequestDto) {

        Coupon coupon = couponRepository.findByCouponUuid(memberCouponCreateRequestDto.getCouponUuid())
                .orElseThrow(() -> new BaseException(ResponseStatus.RESOURCE_NOT_FOUND));

        memberCouponRepository.findByMemberUuidAndCouponUuid(
                memberCouponCreateRequestDto.getMemberUuid(),
                memberCouponCreateRequestDto.getCouponUuid()
        ).ifPresent(
                memberCoupon -> {
                    throw new BaseException(ResponseStatus.ALREADY_ISSUED_COUPON);
                }
        );

        coupon.issue();

        memberCouponRepository.save(memberCouponCreateRequestDto.toEntity());
    }

    @Override
    public CursorPage<MemberCouponResponseDto> getMemberCoupons(MemberCouponRequestDto memberCouponRequestDto) {
        return memberCouponRepository.getAllCouponsByMemberUuid(memberCouponRequestDto)
                .map(MemberCouponResponseDto::from);
    }
}
