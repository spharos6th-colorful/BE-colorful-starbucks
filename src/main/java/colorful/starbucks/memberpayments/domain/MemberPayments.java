package colorful.starbucks.memberpayments.domain;

import colorful.starbucks.payments.domain.Payments;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberPayments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_payments_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payments_id")
    private Payments payments;

    private String cardNumber;

    private String memberUuid;

    @Builder
    private MemberPayments(Long id,
                           Payments payments,
                           String cardNumber,
                           String memberUuid) {
        this.id = id;
        this.payments = payments;
        this.cardNumber = cardNumber;
        this.memberUuid = memberUuid;
    }
}
