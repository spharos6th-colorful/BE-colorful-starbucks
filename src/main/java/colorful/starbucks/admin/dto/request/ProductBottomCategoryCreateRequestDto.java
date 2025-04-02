package colorful.starbucks.admin.dto.request;

import colorful.starbucks.admin.domain.ProductBottomCategory;
import colorful.starbucks.admin.domain.ProductTopCategory;
import colorful.starbucks.admin.vo.request.ProductBottomCategoryCreateRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductBottomCategoryCreateRequestDto {

    private String categoryName;
    private Long topCategoryId;

    @Builder
    private ProductBottomCategoryCreateRequestDto(String categoryName,
                                                  Long topCategoryId) {
        this.categoryName = categoryName;
        this.topCategoryId = topCategoryId;
    }

    public static ProductBottomCategoryCreateRequestDto from(ProductBottomCategoryCreateRequestVo productBottomCategoryCreateRequestVo) {
        return ProductBottomCategoryCreateRequestDto.builder()
                .categoryName(productBottomCategoryCreateRequestVo.getCategoryName())
                .topCategoryId(productBottomCategoryCreateRequestVo.getTopCategoryId())
                .build();
    }

    public ProductBottomCategory toEntity(ProductTopCategory productTopCategory) {
        return ProductBottomCategory.builder()
                .categoryName(categoryName)
                .productTopCategory(productTopCategory)
                .build();
    }
}
