package colorful.starbucks.product.dto.response;

import colorful.starbucks.product.domain.ProductDetail;
import colorful.starbucks.product.vo.response.ProductDetailResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductDetailResponseDto {

    private String productDetailCode;
    private String productCode;
    private String sizeName;
    private String colorName;
    private Integer inventoryQuantity;
    private Integer price;
    private Integer discountPrice;
    private String productDetailThumbnailUrl;

    @Builder
    private ProductDetailResponseDto(String productDetailCode,
                                     String productCode,
                                     String sizeName,
                                     String colorName,
                                     Integer inventoryQuantity,
                                     Integer price,
                                     Integer discountPrice,
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

    public static ProductDetailResponseDto from(ProductDetail productDetail) {
        return ProductDetailResponseDto.builder()
                .productDetailCode(productDetail.getProductDetailCode())
                .productCode(productDetail.getProductCode())
                .sizeName(productDetail.getSizeName())
                .colorName(productDetail.getColorName())
                .inventoryQuantity(productDetail.getInventoryQuantity())
                .price(productDetail.getPrice())
                .discountPrice(productDetail.getDiscountPrice())
                .productDetailThumbnailUrl(productDetail.getProductDetailThumbnailUrl())
                .build();
    }

    public ProductDetailResponseVo toVo() {
        return ProductDetailResponseVo.builder()
                .productDetailCode(productDetailCode)
                .productCode(productCode)
                .sizeName(sizeName)
                .colorName(colorName)
                .inventoryQuantity(inventoryQuantity)
                .price(price)
                .discountPrice(discountPrice)
                .productDetailThumbnailUrl(productDetailThumbnailUrl)
                .build();
    }
}
