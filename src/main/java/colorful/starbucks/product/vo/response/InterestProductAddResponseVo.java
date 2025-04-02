package colorful.starbucks.product.vo.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class InterestProductAddResponseVo {

    private String productCode;
    private String productName;
    private int price;
    private String productThumbnailUrl;

    @Builder
    private InterestProductAddResponseVo(
            String productCode,
            String productName,
            int price,
            String productThumbnailUrl) {
        this.productCode = productCode;
        this.productName = productName;
        this.price = price;
        this.productThumbnailUrl = productThumbnailUrl;
    }
}
