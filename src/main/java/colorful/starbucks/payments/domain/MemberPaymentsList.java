package colorful.starbucks.payments.domain;

import colorful.starbucks.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberPaymentsList extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cardNumber;

    private String memberUuid;

    @Builder
    private MemberPaymentsList(Long id,

                               String cardNumber,
                               String memberUuid) {
        this.id = id;

        this.cardNumber = cardNumber;
        this.memberUuid = memberUuid;
    }
}
