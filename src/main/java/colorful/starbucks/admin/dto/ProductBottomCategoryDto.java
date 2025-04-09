package colorful.starbucks.admin.dto;

import colorful.starbucks.admin.domain.ProductBottomCategory;
import colorful.starbucks.admin.vo.ProductBottomCategoryVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductBottomCategoryDto {

    private Long bottomCategoryId;
    private String categoryName;

    @Builder
    private ProductBottomCategoryDto(Long bottomCategoryId, String categoryName) {
        this.bottomCategoryId = bottomCategoryId;
        this.categoryName = categoryName;
    }

    public static ProductBottomCategoryDto from(ProductBottomCategory productBottomCategory) {
        return ProductBottomCategoryDto.builder()
                .bottomCategoryId(productBottomCategory.getId())
                .categoryName(productBottomCategory.getCategoryName())
                .build();
    }

    public ProductBottomCategoryVo toVo() {
        return ProductBottomCategoryVo.builder()
                .bottomCategoryId(bottomCategoryId)
                .categoryName(categoryName)
                .build();
    }
}
