package colorful.starbucks.admin.dto.request;

import colorful.starbucks.admin.domain.ProductTopCategory;
import colorful.starbucks.admin.vo.request.ProductTopCategoryCreateRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductTopCategoryCreateRequestDto {

    private String categoryName;

    @Builder
    private ProductTopCategoryCreateRequestDto(String categoryName) {
        this.categoryName = categoryName;
    }

    public static ProductTopCategoryCreateRequestDto from(ProductTopCategoryCreateRequestVo productTopCategoryCreateRequestVo) {
        return ProductTopCategoryCreateRequestDto.builder()
                .categoryName(productTopCategoryCreateRequestVo.getCategoryName())
                .build();
    }

    public ProductTopCategory toEntity() {
        return ProductTopCategory.builder()
                .categoryName(categoryName)
                .build();
    }
}
