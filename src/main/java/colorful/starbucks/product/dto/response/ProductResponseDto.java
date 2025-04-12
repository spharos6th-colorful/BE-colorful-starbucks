package colorful.starbucks.product.dto.response;

import colorful.starbucks.product.domain.Product;
import colorful.starbucks.product.vo.response.ProductResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductResponseDto {

    private String productName;
    private Long productCode;
    private String description;
    private Integer price;
    private String productImageUrl;
    private String productThumbnailUrl;
    private Boolean markable;

    @Builder
    public ProductResponseDto(String productName,
                              Long productCode,
                              String description,
                              Integer price,
                              String productImageUrl,
                              String productThumbnailUrl,
                              Boolean markable) {
        this.productName = productName;
        this.productCode = productCode;
        this.description = description;
        this.price = price;
        this.productImageUrl = productImageUrl;
        this.productThumbnailUrl = productThumbnailUrl;
        this.markable = markable;
    }

    public static ProductResponseDto from(Product product) {
        return ProductResponseDto.builder()
                .productName(product.getProductName())
                .productCode(product.getProductCode())
                .description(product.getDescription())
                .price(product.getPrice())
                .productImageUrl(product.getProductImageUrl())
                .productThumbnailUrl(product.getProductThumbnailUrl())
                .markable(product.isMarkable())
                .build();
    }

    public ProductResponseVo toVo() {
        return ProductResponseVo.builder()
                .productName(productName)
                .productCode(productCode)
                .description(description)
                .price(price)
                .productImageUrl(productImageUrl)
                .productThumbnailUrl(productThumbnailUrl)
                .markable(markable)
                .build();
    }
}
