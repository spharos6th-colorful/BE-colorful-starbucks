package colorful.starbucks.product.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InterestProductVo {

    private Long interestProductId;
    private String productCode;
    private String productName;
    private Integer price;
    private String productThumbnailUrl;

    @Builder
    private InterestProductVo(Long interestProductId,
                              String productCode,
                              String productName,
                              Integer price,
                              String productThumbnailUrl) {
        this.interestProductId = interestProductId;
        this.productCode = productCode;
        this.productName = productName;
        this.price = price;
        this.productThumbnailUrl = productThumbnailUrl;
    }
}
