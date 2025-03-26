package colorful.starbucks.product.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductCreateResponseVo {

    private String productName;
    private String productCode;
    private String description;
    private String productCommonImageUrl;
    private String productThumbnailUrl;

    @Builder
    private ProductCreateResponseVo(String productName,
                                    String productCode,
                                    String description,
                                    String productCommonImageUrl,
                                    String productThumbnailUrl) {
        this.productName = productName;
        this.productCode = productCode;
        this.description = description;
        this.productCommonImageUrl = productCommonImageUrl;
        this.productThumbnailUrl = productThumbnailUrl;
    }
}
