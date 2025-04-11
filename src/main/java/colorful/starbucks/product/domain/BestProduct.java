package colorful.starbucks.product.domain;

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
public class BestProduct extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "best_product_id")
    private Long id;

    @Column(nullable = false)
    @Comment(value = "상품 코드")
    private Long productCode;

    @Column(nullable = false)
    private Long categoryId;

    @Column(nullable = false)
    private String categoryName;

    @Column(nullable = false)
    private int totalQuantity;

    @Column(nullable = false)
    private int productRank;

    @Builder
    private BestProduct(Long id,
                        Long productCode,
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
