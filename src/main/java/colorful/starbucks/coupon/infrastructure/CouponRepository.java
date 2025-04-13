package colorful.starbucks.coupon.infrastructure;

import colorful.starbucks.coupon.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

    Optional<Coupon> findByCouponUuid(String couponUuid);

    @Modifying
    @Query("UPDATE Coupon c SET c.currentIssuanceCount = :newIssuanceCount WHERE c.couponUuid = :couponUuid")
    void updateCouponIssuanceCount(String couponUuid, int newIssuanceCount);
}
