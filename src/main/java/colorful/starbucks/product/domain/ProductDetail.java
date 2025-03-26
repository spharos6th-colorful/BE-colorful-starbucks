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

    private Long sizeId;

    private String sizeName;

    private Long colorId;

    private String colorName;

    private String productCode;

    private String productDetailCode;

    private int inventoryQuantity;

    private int price;

    private String productDetailThumbnail;

    @Builder
    public ProductDetail(Long id,
                         Long sizeId,
                         String sizeName,
                         Long colorId,
                         String colorName,
                         String productCode,
                         String productDetailCode,
                         int inventoryQuantity,
                         int price,
                         String productDetailThumbnail) {
        this.id = id;
        this.sizeId = sizeId;
        this.sizeName = sizeName;
        this.colorId = colorId;
        this.colorName = colorName;
        this.productCode = productCode;
        this.productDetailCode = productDetailCode;
        this.inventoryQuantity = inventoryQuantity;
        this.price = price;
        this.productDetailThumbnail = productDetailThumbnail;
    }
}
