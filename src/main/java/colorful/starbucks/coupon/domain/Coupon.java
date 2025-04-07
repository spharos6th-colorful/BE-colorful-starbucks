package colorful.starbucks.coupon.domain;

import colorful.starbucks.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Coupon extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("쿠폰 UUID")
    @Column(nullable = false, unique = true)
    private String couponUuid;

    @Comment("쿠폰 이름")
    @Column(nullable = false, length = 50)
    private String couponName;

    @Comment("쿠폰 설명")
    @Column(nullable = false, length = 200)
    private String couponDescription;

    @Enumerated(EnumType.STRING)
    @Comment("할인 타입")
    @Column(nullable = false)
    private DiscountType discountType;

    @Comment("할인 값, 금액 또는 %")
    @Column(nullable = false)
    private int discountValue;

    @Lob
    @Comment("쿠폰 이미지 URL")
    @Column(nullable = false, length = 200)
    private String couponImageUrl;

    @Comment("최대 할인 금액")
    @Column(nullable = false)
    private int maxDiscountPrice;

    @Comment("최소 주문 금액")
    @Column(nullable = false)
    private int minOrderPrice;

    @Builder
    private Coupon(Long id,
                   String couponUuid,
                   String couponName,
                   DiscountType discountType,
                   int discountValue,
                   String couponImageUrl,
                   int maxDiscountPrice,
                   int minOrderPrice) {
        this.id = id;
        this.couponUuid = couponUuid;
        this.couponName = couponName;
        this.discountType = discountType;
        this.discountValue = discountValue;
        this.couponImageUrl = couponImageUrl;
        this.maxDiscountPrice = maxDiscountPrice;
        this.minOrderPrice = minOrderPrice;
    }
}
