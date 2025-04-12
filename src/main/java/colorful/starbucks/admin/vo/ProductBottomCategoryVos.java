package colorful.starbucks.admin.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ProductBottomCategoryVos {

    private List<ProductBottomCategoryVo> content;
    private Long totalElements;
    private Integer totalPages;

    @Builder
    private ProductBottomCategoryVos(List<ProductBottomCategoryVo> content,
                                     Long totalElements,
                                     Integer totalPages) {
        this.content = content;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }
}
