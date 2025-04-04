package colorful.starbucks.product.vo.request;

import lombok.Getter;

@Getter
public class ProductDetailCreateRequestVo {

    private String productCode;
    private Long sizeId;
    private String sizeName;
    private Long colorId;
    private String colorName;
    private int inventoryQuantity;
    private int price;
    private int discountPrice;
}
