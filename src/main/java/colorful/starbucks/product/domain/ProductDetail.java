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
    @Column(name = "product_detail_id")
    private Long id;

    @Comment("상품 상세 코드")
    @Column(nullable = false, unique = true)
    private String productDetailCode;

    @Comment("상품 코드")
    @Column(nullable = false)
    private String productCode;

    @Comment("색상 ID")
    @Column(nullable = false)
    private Long sizeId;

    @Comment("사이즈 이름")
    @Column(nullable = false)
    private String sizeName;

    @Comment("색상 ID")
    @Column(nullable = false)
    private Long colorId;

    @Comment("색상 이름")
    @Column(nullable = false)
    private String colorName;

    @Comment("재고 수량")
    @Column(nullable = false)
    private int inventoryQuantity;

    @Comment("가격")
    @Column(nullable = false)
    private int price;

    @Comment("상품 상세 썸네일 URL")
    @Column(nullable = false)
    private String productDetailThumbnailUrl;

    @Comment("할인 가격")
    @Column(nullable = false)
    private int discountPrice;

    @Builder
    private ProductDetail(Long id,
                         Long sizeId,
                         String sizeName,
                         Long colorId,
                         String colorName,
                         String productCode,
                         String productDetailCode,
                         int inventoryQuantity,
                         int price,
                         String productDetailThumbnailUrl) {
        this.id = id;
        this.sizeId = sizeId;
        this.sizeName = sizeName;
        this.colorId = colorId;
        this.colorName = colorName;
        this.productCode = productCode;
        this.productDetailCode = productDetailCode;
        this.inventoryQuantity = inventoryQuantity;
        this.price = price;
        this.productDetailThumbnailUrl = productDetailThumbnailUrl;
    }
}
