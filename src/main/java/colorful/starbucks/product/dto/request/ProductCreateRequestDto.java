package colorful.starbucks.product.dto.request;

import colorful.starbucks.product.domain.Product;
import colorful.starbucks.product.vo.request.ProductCreateRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductCreateRequestDto {

    private String productName;
    private String productTopCategoryName;
    private String productBottomCategoryName;
    private String description;
    private boolean markable;
    private int price;

    @Builder
    private ProductCreateRequestDto(String productName,
                                    String productTopCategoryName,
                                    String productBottomCategoryName,
                                    String description,
                                    boolean markable,
                                    int price) {
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
                .markable(productCreateRequestVo.isMarkable())
                .price(productCreateRequestVo.getPrice())
                .build();
    }

    public Product toEntity(String productCode,
                            String productThumbnailUrl,
                            String productCommonImageUrl) {
        return Product.builder()
                .productCode(productCode)
                .productName(productName)
                .description(description)
                .markable(markable)
                .price(price)
                .productImageUrl(productCommonImageUrl)
                .productThumbnailUrl(productThumbnailUrl)
                .build();
    }
}
