package colorful.starbucks.product.domain;

import colorful.starbucks.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductDetail extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_detail_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String productDetailCode;

    @Column(nullable = false, unique = true)
    private String productCode;

    @Column(nullable = false)
    private Long sizeId;

    @Column(nullable = false)
    private String sizeName;

    @Column(nullable = false)
    private Long colorId;

    @Column(nullable = false)
    private String colorName;

    @Column(nullable = false)
    private int inventoryQuantity;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private String productDetailThumbnailUrl;

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
