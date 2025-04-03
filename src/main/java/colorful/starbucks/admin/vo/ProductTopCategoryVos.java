package colorful.starbucks.admin.vo;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ProductTopCategoryVos {

    private List<ProductTopCategoryVo> content;
    private Long totalElements;
    private Integer totalPages;

    @Builder
    private ProductTopCategoryVos(List<ProductTopCategoryVo> content,
                                  Long totalElements,
                                  Integer totalPages) {
        this.content = content;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }
}
