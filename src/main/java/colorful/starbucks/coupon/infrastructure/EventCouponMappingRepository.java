package colorful.starbucks.coupon.infrastructure;

import colorful.starbucks.coupon.domain.EventCouponMapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventCouponMappingRepository extends JpaRepository<EventCouponMapping, Long> {
    Page<EventCouponMapping> findAllByEventUuid(String eventUuid, Pageable pageable);
}
