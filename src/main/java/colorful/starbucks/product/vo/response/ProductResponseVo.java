package colorful.starbucks.product.vo.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductResponseVo {

    private String productName;
    private String productCode;
    private String description;
    private String productImageUrl;
    private String productThumbnailUrl;
    private boolean markable;

    @Builder
    private ProductResponseVo(String productName,
                              String productCode,
                              String description,
                              String productImageUrl,
                              String productThumbnailUrl,
                              boolean markable) {
        this.productName = productName;
        this.productCode = productCode;
        this.description = description;
        this.productImageUrl = productImageUrl;
        this.productThumbnailUrl = productThumbnailUrl;
        this.markable = markable;
    }
}
