package colorful.starbucks.admin.dto;

import colorful.starbucks.admin.domain.ProductBottomCategory;
import colorful.starbucks.admin.vo.ProductBottomCategoryVos;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@NoArgsConstructor
public class ProductBottomCategoryDtos {

    private List<ProductBottomCategoryDto> content;
    private Long totalElements;
    private Integer totalPages;

    @Builder
    private ProductBottomCategoryDtos(List<ProductBottomCategoryDto> content,
                                      Long totalElements,
                                      Integer totalPages) {
        this.content = content;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }

    public static ProductBottomCategoryDtos from(Page<ProductBottomCategory> productBottomCategoryPage) {

        return ProductBottomCategoryDtos.builder()
                .content(
                        productBottomCategoryPage.stream()
                                .map(ProductBottomCategoryDto::from)
                                .toList()
                )
                .totalElements(productBottomCategoryPage.getTotalElements())
                .totalPages(productBottomCategoryPage.getTotalPages())
                .build();
    }

    public ProductBottomCategoryVos toVo() {
        return ProductBottomCategoryVos.builder()
                .content(
                        content.stream()
                                .map(ProductBottomCategoryDto::toVo)
                                .toList()
                )
                .totalElements(totalElements)
                .totalPages(totalPages)
                .build();
    }
}
