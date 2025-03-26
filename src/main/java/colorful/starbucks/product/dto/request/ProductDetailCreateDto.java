package colorful.starbucks.product.dto.request;

import colorful.starbucks.product.domain.ProductDetail;
import colorful.starbucks.product.vo.ProductDetailCreateVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductDetailCreateDto {

    private Long sizeId;
    private Long colorId;
    private int inventoryQuantity;
    private int price;

    @Builder
    private ProductDetailCreateDto(Long sizeId,
                                   Long colorId,
                                   int inventoryQuantity,
                                   int price) {
        this.sizeId = sizeId;
        this.colorId = colorId;
        this.inventoryQuantity = inventoryQuantity;
        this.price = price;
    }

    public static ProductDetailCreateDto from(ProductDetailCreateVo productDetailCreateVo) {
        return ProductDetailCreateDto.builder()
                                     .sizeId(productDetailCreateVo.getSizeId())
                                     .colorId(productDetailCreateVo.getColorId())
                                     .inventoryQuantity(productDetailCreateVo.getInventoryQuantity())
                                     .price(productDetailCreateVo.getPrice())
                                     .build();
    }

    public ProductDetail toEntity(String productCode, String productDetailCode) {
        return ProductDetail.builder()
                            .productCode(productCode)
                            .productDetailCode(productDetailCode)
                            .sizeId(sizeId)
                            .colorId(colorId)
                            .inventoryQuantity(inventoryQuantity)
                            .price(price)
                            .build();
    }
}
