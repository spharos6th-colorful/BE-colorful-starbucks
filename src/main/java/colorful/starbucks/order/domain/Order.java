package colorful.starbucks.order.domain;

import colorful.starbucks.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Orders")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    private String memberUuid;

    private String couponUuid;

    private String couponName;

    private int totalAmount;

    private int discountAmount;

    private String ZoneCode;

    private String address;

    private String detailAddress;

    private Boolean isGift;

    private String buyer;


    @Builder

    private Order(Long id,
                 String memberUuid,
                 String couponUuid,
                 String couponName,
                 int totalAmount,
                 int discountAmount,
                 String zoneCode,
                 String address,
                 String detailAddress,
                 Boolean isGift,
                 String buyer) {
        this.id = id;
        this.memberUuid = memberUuid;
        this.couponUuid = couponUuid;
        this.couponName = couponName;
        this.totalAmount = totalAmount;
        this.discountAmount = discountAmount;
        ZoneCode = zoneCode;
        this.address = address;
        this.detailAddress = detailAddress;
        this.isGift = isGift;
        this.buyer = buyer;
    }
}
