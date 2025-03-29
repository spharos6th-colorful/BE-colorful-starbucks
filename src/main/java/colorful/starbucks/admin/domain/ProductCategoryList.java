package colorful.starbucks.admin.domain;

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
public class ProductCategoryList extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_category_list_id")
    private Long id;

    @Comment("상품 코드")
    @Column(nullable = false)
    private String productCode;

    @Comment("상위 카테고리 ID")
    @Column(nullable = false)
    private Long topCategoryId;

    @Comment("상위 카테고리 이름")
    @Column(nullable = false)
    private String topCategoryName;

    @Comment("하위 카테고리 ID")
    @Column(nullable = false)
    private Long bottomCategoryId;

    @Comment("하위 카테고리 이름")
    @Column(nullable = false)
    private String bottomCategoryName;

    @Builder
    private ProductCategoryList(Long id,
                                String productCode,
                                Long topCategoryId,
                                String topCategoryName,
                                Long bottomCategoryId,
                                String bottomCategoryName) {
        this.id = id;
        this.productCode = productCode;
        this.topCategoryId = topCategoryId;
        this.topCategoryName = topCategoryName;
        this.bottomCategoryId = bottomCategoryId;
        this.bottomCategoryName = bottomCategoryName;
    }
}
