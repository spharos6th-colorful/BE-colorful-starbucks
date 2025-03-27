package colorful.starbucks.product.vo.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class InterestProductCreateResponseVo {

    private String productCode;
    private int price;
    private String productThumbnailUrl;

    @Builder
    private InterestProductCreateResponseVo(
            String productCode,
            int price,
            String productThumbnailUrl) {
        this.productCode = productCode;
        this.price = price;
        this.productThumbnailUrl = productThumbnailUrl;
    }
}
