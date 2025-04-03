package colorful.starbucks.admin.vo.request;

import lombok.Getter;

@Getter
public class ProductBottomCategoryCreateRequestVo {

    private String categoryName;
    private Long topCategoryId;
}
