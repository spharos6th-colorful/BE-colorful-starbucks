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

    private String productCode;

    private Long categoryId;

    private String categoryName;

    private int totalQuantity;

    private int productRank;

    @Builder
    private BestProduct(Long id,
                        String productCode,
                        Long categoryId,
                        String categoryName,
                        int totalQuantity,
                        int productRank) {
        this.id = id;
        this.productCode = productCode;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.totalQuantity = totalQuantity;
        this.productRank = productRank;
    }
}
