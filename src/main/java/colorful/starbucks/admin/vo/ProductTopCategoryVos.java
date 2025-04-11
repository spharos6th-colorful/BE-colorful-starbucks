package colorful.starbucks.admin.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ProductTopCategoryVos {

    private List<ProductTopCategoryVo> categories;

    @Builder
    private ProductTopCategoryVos(List<ProductTopCategoryVo> categories) {
        this.categories = categories;
    }
}
