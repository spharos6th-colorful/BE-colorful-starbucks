package colorful.starbucks.product.domain;

import colorful.starbucks.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class BestProduct extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "best_product_id")
    private Long id;

    private String productUuid;

    private Long categoryId;

    private int totalQauntity;

    private int productRank;

    @Builder
    private BestProduct(Long id, String productUuid, Long categoryId, int totalQauntity, int productRank) {
        this.id = id;
        this.productUuid = productUuid;
        this.categoryId = categoryId;
        this.totalQauntity = totalQauntity;
        this.productRank = productRank;
    }
}
