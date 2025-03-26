package colorful.starbucks.product.dto.request;

import colorful.starbucks.product.domain.Product;
import colorful.starbucks.product.vo.ProductCreateRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductCreateRequestDto {

    private String productName;
    private String productTopCategoryName;
    private String productBottomCategoryName;
    private String description;
    private boolean carvingStatus;
    private int price;

    @Builder
    private ProductCreateRequestDto(String productName,
                                    String productTopCategoryName,
                                    String productBottomCategoryName,
                                    String description,
                                    boolean carvingStatus,
                                    int price) {
        this.productName = productName;
        this.productTopCategoryName = productTopCategoryName;
        this.productBottomCategoryName = productBottomCategoryName;
        this.description = description;
        this.carvingStatus = carvingStatus;
        this.price = price;
    }

    public static ProductCreateRequestDto from(ProductCreateRequestVo productCreateRequestVo) {
        return ProductCreateRequestDto.builder()
                                     .productName(productCreateRequestVo.getProductName())
                                     .productTopCategoryName(productCreateRequestVo.getProductTopCategoryName())
                                     .productBottomCategoryName(productCreateRequestVo.getProductBottomCategoryName())
                                     .description(productCreateRequestVo.getDescription())
                                     .carvingStatus(productCreateRequestVo.isCarvingStatus())
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
                      .carvingStatus(carvingStatus)
                      .price(price)
                      .productThumbnailUrl(productThumbnailUrl)
                      .productCommonImageUrl(productCommonImageUrl)
                      .build();
    }
}
