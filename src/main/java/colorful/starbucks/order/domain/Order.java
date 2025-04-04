package colorful.starbucks.order.domain;

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
@Table(name = "Orders")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Comment("회원 UUID")
    private String memberUuid;

    @Comment("쿠폰 UUID")
    private String couponUuid;

    @Comment("쿠폰 이름")
    private String couponName;

    @Comment("총 결제 금액")
    private int totalAmount;

    @Comment("할인 금액")
    private int discountAmount;

    @Comment("우편 번호")
    private String ZoneCode;

    @Comment("주소")
    private String address;

    @Comment("상세 주소")
    private String detailAddress;

    @Comment("주문 상태")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Comment("선물 여부")
    private Boolean isGift;

    @Comment("구매자")
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
                  OrderStatus orderStatus,
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
        this.orderStatus = orderStatus;
        this.isGift = isGift;
        this.buyer = buyer;
    }
}
