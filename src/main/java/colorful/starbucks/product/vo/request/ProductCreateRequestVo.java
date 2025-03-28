package colorful.starbucks.product.vo.request;

import lombok.Getter;

@Getter
public class ProductCreateRequestVo {

    private String productName;
    private String productTopCategoryName;
    private String productBottomCategoryName;
    private String description;
    private boolean isMarkable;
    private int price;
}
