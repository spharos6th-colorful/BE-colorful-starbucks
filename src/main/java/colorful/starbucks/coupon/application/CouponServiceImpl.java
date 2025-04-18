package colorful.starbucks.coupon.application;

import colorful.starbucks.common.exception.BaseException;
import colorful.starbucks.common.response.ResponseStatus;
import colorful.starbucks.coupon.domain.Coupon;
import colorful.starbucks.coupon.dto.request.CouponCreateRequestDto;
import colorful.starbucks.coupon.dto.response.CouponResponseDto;
import colorful.starbucks.coupon.infrastructure.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CouponServiceImpl implements CouponService{

    private final CouponRepository couponRepository;

    @Transactional
    @Override
    public Coupon createCoupon(CouponCreateRequestDto couponCreateRequestDto) {
        return couponRepository.save(couponCreateRequestDto.toEntity());
    }

    @Transactional
    @Override
    public void issueCoupon(String couponUuid) {

        Coupon coupon = couponRepository.findByCouponUuid(couponUuid)
                .orElseThrow(() -> new BaseException(ResponseStatus.RESOURCE_NOT_FOUND));

        if (isCouponIssuanceLimitExceeded(coupon)) {
            throw new BaseException(ResponseStatus.EXCEED_MAX_COUPON_COUNT);
        }

        couponRepository.updateCouponIssuanceCount(coupon.getCouponUuid(), coupon.getCurrentIssuanceCount() + 1);
    }

    @Override
    public CouponResponseDto getCoupon(String couponUuid) {
        return CouponResponseDto.from(couponRepository.findByCouponUuid(couponUuid)
                .orElseThrow(() -> new BaseException(ResponseStatus.RESOURCE_NOT_FOUND))
        );
    }

    private boolean isCouponIssuanceLimitExceeded(Coupon coupon) {
        return coupon.getCurrentIssuanceCount() >= coupon.getMaxIssuanceLimit();
    }
}
