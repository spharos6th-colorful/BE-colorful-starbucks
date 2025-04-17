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
public class ProductFilter extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("상품 코드")
    @Column(nullable = false)
    private Long productCode;

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

    @Comment("상품 가격")
    @Column(nullable = false)
    private int price;

    @Builder
    private ProductFilter(Long id,
                          Long productCode,
                          Long topCategoryId,
                          String topCategoryName,
                          Long bottomCategoryId,
                          String bottomCategoryName,
                          int price) {
        this.id = id;
        this.productCode = productCode;
        this.topCategoryId = topCategoryId;
        this.topCategoryName = topCategoryName;
        this.bottomCategoryId = bottomCategoryId;
        this.bottomCategoryName = bottomCategoryName;
        this.price = price;
    }
}