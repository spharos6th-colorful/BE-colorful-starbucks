package colorful.starbucks.product.vo.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductResponseVo {

    private String productName;
    private Long productCode;
    private String description;
    private int price;
    private String productImageUrl;
    private String productThumbnailUrl;
    private boolean markable;

    @Builder
    private ProductResponseVo(String productName,
                              Long productCode,
                              String description,
                              int price,
                              String productImageUrl,
                              String productThumbnailUrl,
                              boolean markable) {
        this.productName = productName;
        this.productCode = productCode;
        this.description = description;
        this.price = price;
        this.productImageUrl = productImageUrl;
        this.productThumbnailUrl = productThumbnailUrl;
        this.markable = markable;
    }
}
