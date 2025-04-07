package colorful.starbucks.coupon.application;

import colorful.starbucks.coupon.dto.request.CouponCreateRequestDto;
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
    public void createCoupon(CouponCreateRequestDto couponCreateRequestDto) {
        couponRepository.save(couponCreateRequestDto.toEntity());
    }
}
