package colorful.starbucks.product.dto.request;

import colorful.starbucks.product.domain.Product;
import colorful.starbucks.product.vo.request.ProductCreateRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductCreateRequestDto {

    private String productName;
    private String productTopCategoryName;
    private String productBottomCategoryName;
    private String description;
    private Boolean markable;
    private Integer price;

    @Builder
    private ProductCreateRequestDto(String productName,
                                    String productTopCategoryName,
                                    String productBottomCategoryName,
                                    String description,
                                    Boolean markable,
                                    Integer price) {
        this.productName = productName;
        this.productTopCategoryName = productTopCategoryName;
        this.productBottomCategoryName = productBottomCategoryName;
        this.description = description;
        this.markable = markable;
        this.price = price;
    }

    public static ProductCreateRequestDto from(ProductCreateRequestVo productCreateRequestVo) {
        return ProductCreateRequestDto.builder()
                .productName(productCreateRequestVo.getProductName())
                .productTopCategoryName(productCreateRequestVo.getProductTopCategoryName())
                .productBottomCategoryName(productCreateRequestVo.getProductBottomCategoryName())
                .description(productCreateRequestVo.getDescription())
                .markable(productCreateRequestVo.getMarkable())
                .price(productCreateRequestVo.getPrice())
                .build();
    }

    public Product toEntity(Long productCode,
                            String productThumbnailUrl,
                            String productImageUrl) {
        return Product.builder()
                .productCode(productCode)
                .productName(productName)
                .description(description)
                .markable(markable)
                .price(price)
                .productImageUrl(productImageUrl)
                .productThumbnailUrl(productThumbnailUrl)
                .build();
    }
}
