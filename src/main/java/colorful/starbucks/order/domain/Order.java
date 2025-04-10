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
    private Long id;

    @Comment("주문 코드")
    @Column(nullable = false, unique = true)
    private Long orderCode;

    @Comment("회원 UUID")
    @Column(nullable = false)
    private String memberUuid;

    @Comment("쿠폰 UUID")
    @Column(nullable = true)
    private String couponUuid;

    @Comment("쿠폰 이름")
    @Column(nullable = true)
    private String couponName;

    @Comment("총 결제 금액")
    @Column(nullable = false)
    private int totalAmount;

    @Comment("할인 금액")
    @Column(nullable = true)
    private int discountAmount;

    @Comment("우편 번호")
    @Column(nullable = false)
    private String ZoneCode;

    @Comment("주소")
    @Column(nullable = false)
    private String address;

    @Comment("상세 주소")
    @Column(nullable = false)
    private String detailAddress;

    @Comment("주문 상태")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus orderStatus;

    @Comment("선물 여부")
    @Column(nullable = true)
    private Boolean isGift;

    @Comment("구매자")
    @Column(nullable = true)
    private String buyer;

    @Comment("주문 취소 사유")
    @Enumerated(EnumType.STRING)
    private OrderCancelReason orderCancelReason;

    @Comment("주문 취소 사유 상세")
    @Column(length = 500)
    private String orderCancelReasonDetail;


    @Builder
    private Order(Long id,
                  Long orderCode,
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
                  OrderCancelReason orderCancelReason,
                  String orderCancelReasonDetail,
                  String buyer) {
        this.id = id;
        this.orderCode = orderCode;
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
        this.orderCancelReason = orderCancelReason;
        this.orderCancelReasonDetail = orderCancelReasonDetail;
        this.buyer = buyer;
    }

    public void cancel(OrderCancelReason orderCancelReason, String orderCancelReasonDetail) {
        this.orderStatus = OrderStatus.CANCELLED;
        this.orderCancelReason = orderCancelReason;
        this.orderCancelReasonDetail = orderCancelReasonDetail;
    }
}
