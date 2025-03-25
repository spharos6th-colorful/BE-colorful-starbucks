package colorful.starbucks.payments.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED )
public class Payments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payments_id")
    private Long id;

    private String paymentType;

    @Builder
    private Payments(Long id,
                     String paymentType) {
        this.id = id;
        this.paymentType = paymentType;
    }
}
