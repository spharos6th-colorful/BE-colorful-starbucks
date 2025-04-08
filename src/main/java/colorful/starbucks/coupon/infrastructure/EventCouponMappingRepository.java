package colorful.starbucks.coupon.infrastructure;

import colorful.starbucks.coupon.domain.EventCouponMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventCouponMappingRepository extends JpaRepository<EventCouponMapping, Long> {
}
