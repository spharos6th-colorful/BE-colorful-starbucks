package colorful.starbucks.admin.dto;

import colorful.starbucks.admin.domain.ProductTopCategory;
import colorful.starbucks.admin.vo.ProductTopCategoryVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProductTopCategoryDto that = (ProductTopCategoryDto) o;
        return Objects.equals(topCategoryId, that.topCategoryId) && Objects.equals(categoryName, that.categoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topCategoryId, categoryName);
    }
}
