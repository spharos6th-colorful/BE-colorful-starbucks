package colorful.starbucks.product.domain;

import colorful.starbucks.admin.domain.Color;
import colorful.starbucks.admin.domain.Size;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "size_id")
    private Size size;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color_id")
    private Color color;

    private String productCode;

    private String productDetailCode;

    private String inventoryQuantity;

    private int price;

    private String productDetailThumbnail;

    private String carvingContent;

    @Builder
    private ProductDetail(Long id,
                          Size size,
                          Color color,
                          String productCode,
                          String productDetailCode,
                          String inventoryQuantity,
                          int price,
                          String productDetailThumbnail,
                          String carvingContent) {
        this.id = id;
        this.size = size;
        this.color = color;
        this.productCode = productCode;
        this.productDetailCode = productDetailCode;
        this.inventoryQuantity = inventoryQuantity;
        this.price = price;
        this.productDetailThumbnail = productDetailThumbnail;
        this.carvingContent = carvingContent;
    }
}
