package colorful.starbucks.product.dto.response;

import colorful.starbucks.product.domain.Product;
import colorful.starbucks.product.vo.response.ProductSimpleResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductSimpleResponseDto {

    private String productName;
    private Long productCode;
    private String productThumbnailUrl;
    private Integer price;

    @Builder
    private ProductSimpleResponseDto(String productName,
                                     Long productCode,
                                     String productThumbnailUrl,
                                     Integer price) {
        this.productName = productName;
        this.productCode = productCode;
        this.productThumbnailUrl = productThumbnailUrl;
        this.price = price;
    }

    public static ProductSimpleResponseDto from(Product product) {
        return ProductSimpleResponseDto.builder()
                .productName(product.getProductName())
                .productCode(product.getProductCode())
                .productThumbnailUrl(product.getProductThumbnailUrl())
                .price(product.getPrice())
                .build();
    }

    public ProductSimpleResponseVo toVo() {
        return ProductSimpleResponseVo.builder()
                .productName(productName)
                .productCode(productCode)
                .productThumbnailUrl(productThumbnailUrl)
                .price(price)
                .build();
    }
}
