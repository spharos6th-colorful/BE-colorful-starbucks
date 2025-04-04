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

    private String orderUuid;

    private Long couponId;

    private int beforeDiscountPrice;

    private int afterDiscountPrice;

    private String memberUuid;

    private String postNumber;

    private String mainAddress;

    private String subAddress;

    private boolean payStatus;

    private boolean gifted;

    @Builder
    private Order(
            Long id,
            String orderUuid,
            Long couponId,
            int beforeDiscountPrice,
            int afterDiscountPrice,
            String memberUuid,
            String postNumber,
            String mainAddress,
            String subAddress,
            Boolean payStatus,
            Boolean gifted

    ){
        this.id = id;
        this.orderUuid = orderUuid;
        this.couponId = couponId;
        this.beforeDiscountPrice = beforeDiscountPrice;
        this.afterDiscountPrice = afterDiscountPrice;
        this.memberUuid = memberUuid;
        this.postNumber = postNumber;
        this.mainAddress = mainAddress;
        this.subAddress = subAddress;
        this.payStatus = payStatus;
        this.gifted = gifted;

    }
}
