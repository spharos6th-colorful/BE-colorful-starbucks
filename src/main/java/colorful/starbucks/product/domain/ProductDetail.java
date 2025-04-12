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
public class ProductDetail extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("상품 상세 코드")
    @Column(nullable = false, unique = true)
    private Long productDetailCode;

    @Comment("상품 코드")
    @Column(nullable = false)
    private Long productCode;

    @Comment("색상 ID")
    @Column(nullable = true)
    private Long sizeId;

    @Comment("사이즈 이름")
    @Column(nullable = true)
    private String sizeName;

    @Comment("색상 ID")
    @Column(nullable = true)
    private Long colorId;

    @Comment("색상 이름")
    @Column(nullable = true)
    private String colorName;

    @Comment("재고 수량")
    @Column(nullable = false)
    private int inventoryQuantity;

    @Comment("가격")
    @Column(nullable = false)
    private int price;

    @Lob
    @Comment("상품 상세 썸네일 URL")
    @Column(nullable = false)
    private String productDetailThumbnailUrl;

    @Comment("할인 가격")
    @Column(nullable = false)
    private int discountPrice;

    @Builder
    private ProductDetail(Long id,
                          Long productDetailCode,
                          Long productCode,
                          Long sizeId,
                          String sizeName,
                          Long colorId,
                          String colorName,
                          int inventoryQuantity,
                          int price,
                          String productDetailThumbnailUrl,
                          int discountPrice) {
        this.id = id;
        this.productDetailCode = productDetailCode;
        this.productCode = productCode;
        this.sizeId = sizeId;
        this.sizeName = sizeName;
        this.colorId = colorId;
        this.colorName = colorName;
        this.inventoryQuantity = inventoryQuantity;
        this.price = price;
        this.productDetailThumbnailUrl = productDetailThumbnailUrl;
        this.discountPrice = discountPrice;
    }
}
