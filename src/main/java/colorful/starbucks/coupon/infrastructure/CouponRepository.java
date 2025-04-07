package colorful.starbucks.coupon.infrastructure;

import colorful.starbucks.coupon.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long>
     {
}
