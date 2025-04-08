package colorful.starbucks.coupon.domain;


import colorful.starbucks.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberCoupon extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("쿠폰 uuid")
    @Column(nullable = false)
    private String couponUuid;

    @Comment("회원 uuid")
    @Column(nullable = false)
    private String memberUuid;

    @Comment("쿠폰 사용 여부")
    @Column(nullable = false)
    private Boolean isUsed;

    @Comment("쿠폰 사용 일시")
    @Column(nullable = true)
    private LocalDateTime usedAt;

    @Comment("쿠폰 사용 주문 코드")
    @Column(nullable = true)
    private String usedOrderCode;

    @Builder
    private MemberCoupon(Long id,
                         String couponUuid,
                         String memberUuid,
                         Boolean isUsed,
                         LocalDateTime usedAt,
                         String usedOrderCode) {
        this.id = id;
        this.couponUuid = couponUuid;
        this.memberUuid = memberUuid;
        this.isUsed = isUsed;
        this.usedAt = usedAt;
        this.usedOrderCode = usedOrderCode;
    }
}
