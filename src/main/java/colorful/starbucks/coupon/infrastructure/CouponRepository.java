package colorful.starbucks.coupon.infrastructure;

import colorful.starbucks.coupon.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

    Optional<Coupon> findByCouponUuid(String couponUuid);
}
