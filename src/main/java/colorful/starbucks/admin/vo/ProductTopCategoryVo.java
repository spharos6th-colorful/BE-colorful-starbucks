package colorful.starbucks.admin.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductTopCategoryVo {

    private Long topCategoryId;
    private String categoryName;

    @Builder
    private ProductTopCategoryVo(Long topCategoryId, String categoryName) {
        this.topCategoryId = topCategoryId;
        this.categoryName = categoryName;
    }
}
