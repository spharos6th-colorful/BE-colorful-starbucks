package colorful.starbucks.product.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductResponseVo {

    private String productName;
    private Long productCode;
    private String description;
    private Integer price;
    private String productImageUrl;
    private String productThumbnailUrl;
    private Boolean markable;
    private Boolean isNew;

    @Builder
    private ProductResponseVo(String productName,
                              Long productCode,
                              String description,
                              Integer price,
                              String productImageUrl,
                              String productThumbnailUrl,
                              Boolean markable,
                              Boolean isNew) {
        this.productName = productName;
        this.productCode = productCode;
        this.description = description;
        this.price = price;
        this.productImageUrl = productImageUrl;
        this.productThumbnailUrl = productThumbnailUrl;
        this.markable = markable;
        this.isNew = isNew;
    }
}
