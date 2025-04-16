package colorful.starbucks.product.dto.request;

import colorful.starbucks.product.domain.ProductFilter;
import colorful.starbucks.product.vo.request.ProductFilterCreateRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductFilterCreateRequestDto {
    private Long productCode;
    private Long topCategoryId;
    private String topCategoryName;
    private Long bottomCategoryId;
    private String bottomCategoryName;
    private Integer price;

    @Builder
    private ProductFilterCreateRequestDto(Long productCode,
                                          Long topCategoryId,
                                          String topCategoryName,
                                          Long bottomCategoryId,
                                          String bottomCategoryName,
                                          Integer price) {
        this.productCode = productCode;
        this.topCategoryId = topCategoryId;
        this.topCategoryName = topCategoryName;
        this.bottomCategoryId = bottomCategoryId;
        this.bottomCategoryName = bottomCategoryName;
        this.price = price;
    }

    public static ProductFilterCreateRequestDto from(
            ProductFilterCreateRequestVo productFilterCreateRequestVo
    ) {
        return ProductFilterCreateRequestDto.builder()
                .productCode(productFilterCreateRequestVo.getProductCode())
                .topCategoryId(productFilterCreateRequestVo.getTopCategoryId())
                .topCategoryName(productFilterCreateRequestVo.getTopCategoryName())
                .bottomCategoryId(productFilterCreateRequestVo.getBottomCategoryId())
                .bottomCategoryName(productFilterCreateRequestVo.getBottomCategoryName())
                .price(productFilterCreateRequestVo.getPrice())
                .build();
    }

    public ProductFilter toEntity() {
        return ProductFilter.builder()
                .productCode(productCode)
                .topCategoryId(topCategoryId)
                .topCategoryName(topCategoryName)
                .bottomCategoryId(bottomCategoryId)
                .bottomCategoryName(bottomCategoryName)
                .price(price)
                .build();
    }

}
