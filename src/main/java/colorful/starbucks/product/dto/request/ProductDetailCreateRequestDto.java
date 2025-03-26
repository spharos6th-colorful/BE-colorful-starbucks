package colorful.starbucks.product.dto.request;

import colorful.starbucks.product.domain.ProductDetail;
import colorful.starbucks.product.vo.ProductDetailCreateRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductDetailCreateRequestDto {

    private Long sizeId;
    private String sizeName;
    private Long colorId;
    private String colorName;
    private int inventoryQuantity;
    private int price;

    @Builder
    private ProductDetailCreateRequestDto(Long sizeId,
                                          String sizeName,
                                          Long colorId,
                                          String colorName,
                                          int inventoryQuantity,
                                          int price) {
        this.sizeId = sizeId;
        this.sizeName = sizeName;
        this.colorId = colorId;
        this.colorName = colorName;
        this.inventoryQuantity = inventoryQuantity;
        this.price = price;
    }

    public static ProductDetailCreateRequestDto from(ProductDetailCreateRequestVo productDetailCreateRequestVo) {
        return ProductDetailCreateRequestDto.builder()
                                            .sizeId(productDetailCreateRequestVo.getSizeId())
                                            .sizeName(productDetailCreateRequestVo.getSizeName())
                                            .colorId(productDetailCreateRequestVo.getColorId())
                                            .colorName(productDetailCreateRequestVo.getColorName())
                                            .inventoryQuantity(productDetailCreateRequestVo.getInventoryQuantity())
                                            .price(productDetailCreateRequestVo.getPrice())
                                            .build();
    }

    public ProductDetail toEntity(String productCode,
                                  String productDetailCode,
                                  String productDetailThumbnailUrl) {
        return ProductDetail.builder()
                            .productCode(productCode)
                            .productDetailCode(productDetailCode)
                            .sizeId(sizeId)
                            .sizeName(sizeName)
                            .colorId(colorId)
                            .colorName(colorName)
                            .inventoryQuantity(inventoryQuantity)
                            .price(price)
                            .productDetailThumbnailUrl(productDetailThumbnailUrl)
                            .build();
    }
}
