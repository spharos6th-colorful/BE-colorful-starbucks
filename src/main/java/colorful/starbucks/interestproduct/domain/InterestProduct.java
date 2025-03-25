package colorful.starbucks.interestproduct.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InterestProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interest_product_id")
    private Long id;

    private String memberUuid;
    private String productCode;

    @Builder
    private InterestProduct(Long id,
                            String memberUuid,
                            String productCode) {
        this.id = id;
        this.memberUuid = memberUuid;
        this.productCode = productCode;
    }
}
