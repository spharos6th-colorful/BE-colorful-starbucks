package colorful.starbucks.admin.vo.request;

import lombok.Getter;

@Getter
public class ProductCategoryListCreateRequestVo {

    private Long productCode;
    private Long topCategoryId;
    private String topCategoryName;
    private Long bottomCategoryId;
    private String bottomCategoryName;
}
