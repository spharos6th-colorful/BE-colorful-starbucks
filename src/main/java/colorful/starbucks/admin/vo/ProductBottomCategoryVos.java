package colorful.starbucks.admin.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ProductBottomCategoryVos {

    private List<ProductBottomCategoryVo> categories;

    @Builder
    private ProductBottomCategoryVos(List<ProductBottomCategoryVo> categories) {
        this.categories = categories;
    }
}
