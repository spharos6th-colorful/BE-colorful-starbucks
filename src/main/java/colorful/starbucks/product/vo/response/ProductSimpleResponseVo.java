package colorful.starbucks.product.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductSimpleResponseVo {

    private String productName;
    private Long productCode;
    private String productThumbnailUrl;
    private Integer price;
    private Boolean isNew;

    @Builder
    private ProductSimpleResponseVo(String productName,
                                    Long productCode,
                                    String productThumbnailUrl,
                                    Integer price,
                                    Boolean isNew) {
        this.productName = productName;
        this.productCode = productCode;
        this.productThumbnailUrl = productThumbnailUrl;
        this.price = price;
        this.isNew = isNew;
    }
}
