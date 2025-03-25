package colorful.starbucks.product.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    private String productName;

    private String description;

    private String productCode;

    private String productCommonImage;

    private String productThumbnail;

    private boolean carving_status;

    @Builder
    private Product(Long id,
                    String productName,
                    String description,
                    String productCode,
                    String productCommonImage,
                    String productThumbnail,
                    boolean carving_status) {
        this.id = id;
        this.productName = productName;
        this.description = description;
        this.productCode = productCode;
        this.productCommonImage = productCommonImage;
        this.productThumbnail = productThumbnail;
        this.carving_status = carving_status;
    }
}
