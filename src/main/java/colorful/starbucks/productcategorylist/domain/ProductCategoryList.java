package colorful.starbucks.productcategorylist.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductCategoryList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_category_list_id")
    private Long id;

    private String productCode;

    private Long categoryTopId;

    private Long categoryBottomId;

    @Builder
    private ProductCategoryList(Long id,
                               String productCode,
                               Long categoryTopId,
                               Long categoryBottomId) {
        this.id = id;
        this.productCode = productCode;
        this.categoryTopId = categoryTopId;
        this.categoryBottomId = categoryBottomId;
    }
}
