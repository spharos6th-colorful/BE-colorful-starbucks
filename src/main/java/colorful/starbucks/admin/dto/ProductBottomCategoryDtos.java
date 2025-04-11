package colorful.starbucks.admin.dto;

import colorful.starbucks.admin.domain.ProductBottomCategory;
import colorful.starbucks.admin.vo.ProductBottomCategoryVos;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ProductBottomCategoryDtos {

    private List<ProductBottomCategoryDto> categories;

    @Builder
    private ProductBottomCategoryDtos(List<ProductBottomCategoryDto> categories) {
        this.categories = categories;
    }

    public static ProductBottomCategoryDtos from(List<ProductBottomCategory> productBottomCategoryPage) {

        return ProductBottomCategoryDtos.builder()
                .categories(
                        productBottomCategoryPage.stream()
                                .map(ProductBottomCategoryDto::from)
                                .toList()
                )
                .build();
    }

    public ProductBottomCategoryVos toVo() {
        return ProductBottomCategoryVos.builder()
                .categories(
                        categories.stream()
                                .map(ProductBottomCategoryDto::toVo)
                                .toList()
                )
                .build();
    }
}
