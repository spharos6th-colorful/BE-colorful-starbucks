package colorful.starbucks.product.dto.response;

import colorful.starbucks.product.domain.Product;
import colorful.starbucks.product.vo.ProductCreateResponseVo;
import lombok.Builder;

public class ProductCreateResponseDto {

    private String productName;
    private String productCode;
    private String description;
    private String productCommonImageUrl;
    private String productThumbnailUrl;

    @Builder
    private ProductCreateResponseDto(String productName,
                                     String productCode,
                                     String description,
                                     String productCommonImageUrl,
                                     String productThumbnailUrl) {
        this.productName = productName;
        this.productCode = productCode;
        this.description = description;
        this.productCommonImageUrl = productCommonImageUrl;
        this.productThumbnailUrl = productThumbnailUrl;
    }

    public static ProductCreateResponseDto from(Product product) {
        return ProductCreateResponseDto.builder()
                                      .productName(product.getProductName())
                                      .productCode(product.getProductCode())
                                      .description(product.getDescription())
                                      .productCommonImageUrl(product.getProductCommonImageUrl())
                                      .productThumbnailUrl(product.getProductThumbnailUrl())
                .build();
    }

    public ProductCreateResponseVo toVo() {
        return ProductCreateResponseVo.builder()
                                      .productName(productName)
                                      .productCode(productCode)
                                      .description(description)
                                      .productCommonImageUrl(productCommonImageUrl)
                                      .productThumbnailUrl(productThumbnailUrl)
                .build();
    }
}
