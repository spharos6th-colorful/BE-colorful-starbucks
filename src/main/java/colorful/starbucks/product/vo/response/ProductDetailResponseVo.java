package colorful.starbucks.product.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductDetailResponseVo {

    private Long productDetailCode;
    private Long productCode;
    private Long sizeId;
    private String sizeName;
    private Long colorId;
    private String colorName;
    private Integer inventoryQuantity;
    private Integer price;
    private Integer discountPrice;
    private String productDetailThumbnailUrl;

    @Builder
    private ProductDetailResponseVo(Long productDetailCode,
                                    Long productCode,
                                    Long sizeId,
                                    String sizeName,
                                    Long colorId,
                                    String colorName,
                                    Integer inventoryQuantity,
                                    Integer price,
                                    Integer discountPrice,
                                    String productDetailThumbnailUrl) {
        this.productDetailCode = productDetailCode;
        this.productCode = productCode;
        this.sizeId = sizeId;
        this.sizeName = sizeName;
        this.colorId = colorId;
        this.colorName = colorName;
        this.inventoryQuantity = inventoryQuantity;
        this.price = price;
        this.discountPrice = discountPrice;
        this.productDetailThumbnailUrl = productDetailThumbnailUrl;
    }
}
