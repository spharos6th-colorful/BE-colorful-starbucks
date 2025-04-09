package colorful.starbucks.admin.dto;

import colorful.starbucks.admin.domain.ProductTopCategory;
import colorful.starbucks.admin.vo.ProductTopCategoryVos;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@NoArgsConstructor
public class ProductTopCategoryDtos {

    private List<ProductTopCategoryDto> content;
    private Long totalElements;
    private Integer totalPages;

    @Builder
    private ProductTopCategoryDtos(List<ProductTopCategoryDto> content,
                                   Long totalElements,
                                   Integer totalPages) {
        this.content = content;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }

    public static ProductTopCategoryDtos from(Page<ProductTopCategory> productTopCategoryPage) {

        return ProductTopCategoryDtos.builder()
                .content(
                        productTopCategoryPage.stream()
                                .map(ProductTopCategoryDto::from)
                                .toList()
                )
                .totalElements(productTopCategoryPage.getTotalElements())
                .totalPages(productTopCategoryPage.getTotalPages())
                .build();
    }

    public ProductTopCategoryVos toVo() {
        return ProductTopCategoryVos.builder()
                .content(
                        content.stream()
                                .map(ProductTopCategoryDto::toVo)
                                .toList()
                )
                .totalElements(totalElements)
                .totalPages(totalPages)
                .build();
    }
}
