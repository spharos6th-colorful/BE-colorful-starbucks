package colorful.starbucks.admin.dto;

import colorful.starbucks.admin.domain.ProductTopCategory;
import colorful.starbucks.admin.vo.ProductTopCategoryVos;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ProductTopCategoryDtos {

    private List<ProductTopCategoryDto> categories;

    @Builder
    private ProductTopCategoryDtos(List<ProductTopCategoryDto> categories) {
        this.categories = categories;
    }

    public static ProductTopCategoryDtos from(List<ProductTopCategory> productTopCategories) {

        return ProductTopCategoryDtos.builder()
                .categories(
                        productTopCategories.stream()
                                .map(ProductTopCategoryDto::from)
                                .toList()
                )
                .build();
    }

    public ProductTopCategoryVos toVo() {
        return ProductTopCategoryVos.builder()
                .categories(
                        categories.stream()
                                .map(ProductTopCategoryDto::toVo)
                                .toList()
                )
                .build();
    }
}
