package colorful.starbucks.product.dto.response;

import colorful.starbucks.product.domain.Product;
import colorful.starbucks.product.vo.response.ProductSimpleResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ProductSimpleResponseDto {

    private String productName;
    private Long productCode;
    private String productThumbnailUrl;
    private Integer price;
    private Boolean isNew;

    @Builder
    private ProductSimpleResponseDto(String productName,
                                     Long productCode,
                                     String productThumbnailUrl,
                                     Integer price,
                                     Boolean isNew) {
        this.productName = productName;
        this.productCode = productCode;
        this.productThumbnailUrl = productThumbnailUrl;
        this.price = price;
        this.isNew = isNew;
    }

    public static ProductSimpleResponseDto from(Product product) {

        Boolean isNew = product.getCreatedAt().isAfter(LocalDateTime.now().minusDays(3));
        return ProductSimpleResponseDto.builder()
                .productName(product.getProductName())
                .productCode(product.getProductCode())
                .productThumbnailUrl(product.getProductThumbnailUrl())
                .price(product.getPrice())
                .isNew(isNew)
                .build();
    }

    public ProductSimpleResponseVo toVo() {
        return ProductSimpleResponseVo.builder()
                .productName(productName)
                .productCode(productCode)
                .productThumbnailUrl(productThumbnailUrl)
                .price(price)
                .isNew(isNew)
                .build();
    }
}
