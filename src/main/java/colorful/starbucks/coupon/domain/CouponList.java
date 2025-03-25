package colorful.starbucks.coupon.domain;

import colorful.starbucks.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class CouponList extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_list_id")
    private Long id;

    private String couponUuid;

    private String eventUuid;

    private LocalDateTime couponEndDate;

    private int endQuantity;

    @Builder
    public CouponList(Long id, String couponUuid, String eventUuid, LocalDateTime couponEndDate, int endQuantity) {
        this.id = id;
        this.couponUuid = couponUuid;
        this.eventUuid = eventUuid;
        this.couponEndDate = couponEndDate;
        this.endQuantity = endQuantity;
    }


}
