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
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String productCode;

    @Column(nullable = false, length = 150)
    private String productName;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private String description;

    @Lob
    @Column(nullable = false)
    private String productCommonImageUrl;

    @Column(nullable = false)
    private String productThumbnailUrl;

    @Column(nullable = false)
    private boolean carvingStatus;

    @Builder
    public Product(Long id,
                   String productCode,
                   String productName,
                   int price,
                   String description,
                   String productCommonImageUrl,
                   String productThumbnailUrl,
                   boolean carvingStatus) {
        this.id = id;
        this.productCode = productCode;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.productCommonImageUrl = productCommonImageUrl;
        this.productThumbnailUrl = productThumbnailUrl;
        this.carvingStatus = carvingStatus;
    }
}
