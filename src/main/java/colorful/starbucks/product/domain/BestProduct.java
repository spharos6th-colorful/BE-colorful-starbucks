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
    private Long id;

    @Comment(value = "상품 코드")
    @Column(nullable = false)
    private Long productCode;

    @Comment("카테고리 ID")
    @Column(nullable = false)
    private Long categoryId;

    @Comment("카테고리명")
    @Column(nullable = false)
    private String categoryName;

    @Comment("판매 수량")
    @Column(nullable = false)
    private int totalQuantity;

    @Comment("랭킹")
    @Column(nullable = false)
    private int ranking;

    @Builder
    private BestProduct(Long id,
                        Long productCode,
                        Long categoryId,
                        String categoryName,
                        int totalQuantity,
                        int ranking) {
        this.id = id;
        this.productCode = productCode;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.totalQuantity = totalQuantity;
        this.ranking = ranking;
    }
}
