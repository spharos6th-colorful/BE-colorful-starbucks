package colorful.starbucks.product.vo.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductCreateRequestVo {

    private String productName;
    private String productTopCategoryName;
    private String productBottomCategoryName;
    private String description;
    private boolean markable;
    private int price;

    @Builder
    public ProductCreateRequestVo(String productName, String productTopCategoryName, String productBottomCategoryName, String description, boolean markable, int price) {
        this.productName = productName;
        this.productTopCategoryName = productTopCategoryName;
        this.productBottomCategoryName = productBottomCategoryName;
        this.description = description;
        this.markable = markable;
        this.price = price;
    }
}
