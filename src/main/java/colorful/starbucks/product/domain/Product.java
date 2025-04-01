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
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("상품 코드")
    @Column(nullable = false, unique = true)
    private Long productCode;

    @Comment("상품명")
    @Column(nullable = false, length = 150)
    private String productName;

    @Comment("상품 가격")
    @Column(nullable = false)
    private int price;

    @Comment("상품 설명")
    @Column(nullable = false)
    private String description;

    @Lob
    @Comment("상품 이미지 URL")
    @Column(nullable = false)
    private String productImageUrl;


    @Comment("상품 썸네일 URL")
    @Column(nullable = false)
    private String productThumbnailUrl;

    @Comment("상품 각인 가능 여부")
    @Column(nullable = false, columnDefinition = "Tinyint(1) default 0")
    private boolean markable;

    @Builder
    public Product(Long id,
                   Long productCode,
                   String productName,
                   int price,
                   String description,
                   String productImageUrl,
                   String productThumbnailUrl,
                   boolean markable) {
        this.id = id;
        this.productCode = productCode;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.productImageUrl = productImageUrl;
        this.productThumbnailUrl = productThumbnailUrl;
        this.markable = markable;
    }

    public void initProductCode(Long productCode) {
        this.productCode = Long.valueOf(String.valueOf(productCode) + this.id);
    }
}
