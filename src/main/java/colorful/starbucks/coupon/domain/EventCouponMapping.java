package colorful.starbucks.coupon.domain;

import colorful.starbucks.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        uniqueConstraints = {
                @UniqueConstraint(name = "unique_event_coupon_mapping",
                        columnNames = {"couponUuid", "eventUuid"})
        }
)
public class EventCouponMapping extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("쿠폰 UUID")
    @Column(nullable = false)
    private String couponUuid;

    @Comment("이벤트 UUID")
    @Column(nullable = false)
    private String eventUuid;

    @Builder
    private EventCouponMapping(Long id, String couponUuid, String eventUuid) {
        this.id = id;
        this.couponUuid = couponUuid;
        this.eventUuid = eventUuid;
    }
}
