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
public class ProductBottomCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_bottom_category_id")
    private Long id;

    @Comment("하위 카테고리 이름")
    @Column(nullable = false, unique = true)
    private String categoryName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_top_category_id")
    private ProductTopCategory productTopCategory;

    @Builder
    public ProductBottomCategory(Long id,
                                 String categoryName,
                                 ProductTopCategory productTopCategory) {
        this.id = id;
        this.categoryName = categoryName;
        this.productTopCategory = productTopCategory;
    }
}
