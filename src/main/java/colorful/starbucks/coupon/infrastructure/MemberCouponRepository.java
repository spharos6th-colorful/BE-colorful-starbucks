package colorful.starbucks.coupon.infrastructure;

import colorful.starbucks.coupon.domain.MemberCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberCouponRepository extends JpaRepository<MemberCoupon, Long>, MemberCouponRepositoryCustom {
    Optional<MemberCoupon> findByMemberUuidAndCouponUuid(String memberUuid, String couponUuid);

    boolean existsByMemberUuidAndCouponUuid(String memberUuid, String couponUuid);
}
