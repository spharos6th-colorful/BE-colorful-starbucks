package colorful.starbucks.payments.domain;

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
public class PaymentHistory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("총 결제 금액")
    @Column(nullable = false)
    private int totalPrice;

    @Comment("결제 번호")
    @Column(nullable = false, unique = true)
    private String paymentsNumber;

    @Comment("주문 번호")
    @Column(nullable = false, unique = true)
    private String orderCode;

    @Comment("회원 UUID")
    @Column(nullable = false)
    private String memberUuid;

    @Comment("결제 수단")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentsType paymentsType;

    @Builder
    private PaymentHistory(Long id,
                           int totalPrice,
                           String paymentsNumber,
                           String orderCode,
                           String memberUuid,
                           PaymentsType paymentsType) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.paymentsNumber = paymentsNumber;
        this.orderCode = orderCode;
        this.memberUuid = memberUuid;
        this.paymentsType = paymentsType;
    }
}
