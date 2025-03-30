package colorful.starbucks.product.vo.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductDetailResponseVo {

    private String productDetailCode;
    private String productCode;
    private String sizeName;
    private String colorName;
    private int inventoryQuantity;
    private int price;
    private int discountPrice;
    private String productDetailThumbnailUrl;

    @Builder
    private ProductDetailResponseVo(String productDetailCode,
                                    String productCode,
                                    String sizeName,
                                    String colorName,
                                    int inventoryQuantity,
                                    int price,
                                    int discountPrice,
                                    String productDetailThumbnailUrl) {
        this.productDetailCode = productDetailCode;
        this.productCode = productCode;
        this.sizeName = sizeName;
        this.colorName = colorName;
        this.inventoryQuantity = inventoryQuantity;
        this.price = price;
        this.discountPrice = discountPrice;
        this.productDetailThumbnailUrl = productDetailThumbnailUrl;
    }
}
