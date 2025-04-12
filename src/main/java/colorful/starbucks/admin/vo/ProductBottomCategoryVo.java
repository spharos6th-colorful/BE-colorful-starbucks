package colorful.starbucks.admin.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductBottomCategoryVo {

    private Long bottomCategoryId;
    private String categoryName;

    @Builder
    private ProductBottomCategoryVo(Long bottomCategoryId, String categoryName) {
        this.bottomCategoryId = bottomCategoryId;
        this.categoryName = categoryName;
    }
}
