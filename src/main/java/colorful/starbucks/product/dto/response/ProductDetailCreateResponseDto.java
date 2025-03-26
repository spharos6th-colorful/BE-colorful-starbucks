package colorful.starbucks.product.dto.response;

import colorful.starbucks.product.domain.ProductDetail;
import colorful.starbucks.product.vo.response.ProductDetailCreateResponseVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductDetailCreateResponseDto {

    private String productDetailCode;
    private String productCode;
    private String sizeName;
    private String colorName;
    private int inventoryQuantity;
    private int price;
    private String productDetailThumbnailUrl;

    @Builder
    private ProductDetailCreateResponseDto(String productDetailCode,
                                          String productCode,
                                          String sizeName,
                                          String colorName,
                                          int inventoryQuantity,
                                          int price,
                                          String productDetailThumbnailUrl) {
        this.productDetailCode = productDetailCode;
        this.productCode = productCode;
        this.sizeName = sizeName;
        this.colorName = colorName;
        this.inventoryQuantity = inventoryQuantity;
        this.price = price;
        this.productDetailThumbnailUrl = productDetailThumbnailUrl;
    }

    public static ProductDetailCreateResponseDto from(ProductDetail productDetail) {
        return ProductDetailCreateResponseDto.builder()
                                             .productDetailCode(productDetail.getProductDetailCode())
                                             .productCode(productDetail.getProductCode())
                                             .sizeName(productDetail.getSizeName())
                                             .colorName(productDetail.getColorName())
                                             .inventoryQuantity(productDetail.getInventoryQuantity())
                                             .price(productDetail.getPrice())
                                             .productDetailThumbnailUrl(productDetail.getProductDetailThumbnailUrl())
                                             .build();
    }

    public ProductDetailCreateResponseVo toVo() {
        return ProductDetailCreateResponseVo.builder()
                                             .productDetailCode(productDetailCode)
                                             .productCode(productCode)
                                             .sizeName(sizeName)
                                             .colorName(colorName)
                                             .inventoryQuantity(inventoryQuantity)
                                             .price(price)
                                             .productDetailThumbnailUrl(productDetailThumbnailUrl)
                                             .build();
    }
}
