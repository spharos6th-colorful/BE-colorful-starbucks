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
public class PaymentsList extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payments_list_id")
    private Long id;

    private int totalPrice;

    private String paymentsNumber;

     private String orderUuid;

     private String memberUuid;

     @Builder
        private PaymentsList(Long id,
                            int totalPrice,
                            String paymentsNumber,
                            String orderUuid,
                            String memberUuid) {
            this.id = id;
            this.totalPrice = totalPrice;
            this.paymentsNumber = paymentsNumber;
            this.orderUuid = orderUuid;
            this.memberUuid = memberUuid;
        }
}
