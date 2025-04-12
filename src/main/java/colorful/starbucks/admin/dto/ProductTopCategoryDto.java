package colorful.starbucks.admin.dto;

import colorful.starbucks.admin.domain.ProductTopCategory;
import colorful.starbucks.admin.vo.ProductTopCategoryVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductTopCategoryDto {

    private Long topCategoryId;
    private String categoryName;

    @Builder
    private ProductTopCategoryDto(Long topCategoryId, String categoryName) {
        this.topCategoryId = topCategoryId;
        this.categoryName = categoryName;
    }

    public static ProductTopCategoryDto from(ProductTopCategory productTopCategory) {
        return ProductTopCategoryDto.builder()
                .topCategoryId(productTopCategory.getId())
                .categoryName(productTopCategory.getCategoryName())
                .build();
    }

    public ProductTopCategoryVo toVo() {
        return ProductTopCategoryVo.builder()
                .topCategoryId(topCategoryId)
                .categoryName(categoryName)
                .build();
    }
}
