package colorful.starbucks.product.dto.response;

import colorful.starbucks.product.domain.Product;
import colorful.starbucks.product.vo.response.ProductResponseVo;
import lombok.Builder;

public class ProductResponseDto {

    private String productName;
    private String productCode;
    private String description;
    private String productImageUrl;
    private String productThumbnailUrl;
    private boolean markable;

    @Builder
    private ProductResponseDto(String productName,
                               String productCode,
                               String description,
                               String productImageUrl,
                               String productThumbnailUrl,
                               boolean markable) {
        this.productName = productName;
        this.productCode = productCode;
        this.description = description;
        this.productImageUrl = productImageUrl;
        this.productThumbnailUrl = productThumbnailUrl;
        this.markable = markable;
    }

    public static ProductResponseDto from(Product product) {
        return ProductResponseDto.builder()
                .productName(product.getProductName())
                .productCode(product.getProductCode())
                .description(product.getDescription())
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
                .productImageUrl(productImageUrl)
                .productThumbnailUrl(productThumbnailUrl)
                .markable(markable)
                .build();
    }
}
