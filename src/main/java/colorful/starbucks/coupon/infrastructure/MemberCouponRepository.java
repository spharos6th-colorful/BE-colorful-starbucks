package colorful.starbucks.coupon.infrastructure;

import colorful.starbucks.coupon.domain.MemberCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface MemberCouponRepository extends JpaRepository<MemberCoupon, Long>, MemberCouponRepositoryCustom {
    Optional<MemberCoupon> findByMemberUuidAndCouponUuid(String memberUuid, String couponUuid);

    boolean existsByMemberUuidAndCouponUuid(String memberUuid, String couponUuid);

    @Modifying
    @Query(
            "UPDATE MemberCoupon mc " +
                    "SET mc.isUsed = true, mc.usedAt = :usedAt " +
                    "WHERE mc.couponUuid = :couponUuid AND mc.memberUuid = :memberUuid"
    )
    void updateMemberStateToUsed(String couponUuid, String memberUuid, LocalDateTime usedAt);
}
