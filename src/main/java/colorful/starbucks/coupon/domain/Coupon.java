package colorful.starbucks.coupon.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    private Long id;

    private String couponName;

    private String couponImage;

    private int couponPercent;

    private int couponPrice;

    private int maxPrice;

    private int minOrderPrice;

    private String couponUuid;

    @Builder
    private Coupon(Long id,
                   String couponName,
                   String couponImage,
                   int couponPercent,
                   int couponPrice,
                   int maxPrice,
                   int minOrderPrice,
                   String couponUuid) {
        this.id = id;
        this.couponName = couponName;
        this.couponImage = couponImage;
        this.couponPercent = couponPercent;
        this.couponPrice = couponPrice;
        this.maxPrice = maxPrice;
        this.minOrderPrice = minOrderPrice;
        this.couponUuid = couponUuid;
    }

}
