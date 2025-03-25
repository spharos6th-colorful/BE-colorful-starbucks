package colorful.starbucks.coupon.domain;


import colorful.starbucks.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MemberCoupon extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_coupon_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_list_id")
    private CouponList couponList;

    private String memberUuid;

    private Boolean couponUseState;

    @Builder
    private MemberCoupon(Long id, CouponList couponList, String memberUuid, Boolean couponUseState) {
        this.id = id;
        this.couponList = couponList;
        this.memberUuid = memberUuid;
        this.couponUseState = couponUseState;
    }
}
