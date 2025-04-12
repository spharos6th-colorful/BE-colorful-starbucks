package colorful.starbucks.product.dto.request;

import colorful.starbucks.product.domain.ProductDetail;
import colorful.starbucks.product.vo.request.ProductDetailCreateRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductDetailCreateRequestDto {

    private String productCode;
    private Long sizeId;
    private String sizeName;
    private Long colorId;
    private String colorName;
    private Integer inventoryQuantity;
    private Integer price;
    private Integer discountPrice;

    @Builder
    private ProductDetailCreateRequestDto(String productCode,
                                          Long sizeId,
                                          String sizeName,
                                          Long colorId,
                                          String colorName,
                                          Integer inventoryQuantity,
                                          Integer price,
                                          Integer discountPrice) {
        this.productCode = productCode;
        this.sizeId = sizeId;
        this.sizeName = sizeName;
        this.colorId = colorId;
        this.colorName = colorName;
        this.inventoryQuantity = inventoryQuantity;
        this.price = price;
        this.discountPrice = discountPrice;
    }

    public static ProductDetailCreateRequestDto from(ProductDetailCreateRequestVo productDetailCreateRequestVo) {
        return ProductDetailCreateRequestDto.builder()
                .productCode(productDetailCreateRequestVo.getProductCode())
                .sizeId(productDetailCreateRequestVo.getSizeId())
                .sizeName(productDetailCreateRequestVo.getSizeName())
                .colorId(productDetailCreateRequestVo.getColorId())
                .colorName(productDetailCreateRequestVo.getColorName())
                .inventoryQuantity(productDetailCreateRequestVo.getInventoryQuantity())
                .price(productDetailCreateRequestVo.getPrice())
                .discountPrice(productDetailCreateRequestVo.getDiscountPrice())
                .build();
    }

    public ProductDetail toEntity(Long productDetailCode,
                                  String productDetailThumbnailUrl) {
        return ProductDetail.builder()
                .productDetailCode(productDetailCode)
                .productCode(productCode)
                .sizeId(sizeId)
                .sizeName(sizeName)
                .colorId(colorId)
                .colorName(colorName)
                .inventoryQuantity(inventoryQuantity)
                .price(price)
                .discountPrice(discountPrice)
                .productDetailThumbnailUrl(productDetailThumbnailUrl)
                .build();
    }
}
