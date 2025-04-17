package colorful.starbucks.product.vo.request;

import lombok.Getter;

@Getter
public class ProductFilterCreateRequestVo {

    private Long productCode;
    private Long topCategoryId;
    private String topCategoryName;
    private Long bottomCategoryId;
    private String bottomCategoryName;
    private Integer price;
}
