package colorful.starbucks.product.vo;

import lombok.Getter;

@Getter
public class ProductDetailCreateVo {

    private Long sizeId;
    private Long colorId;
    private int inventoryQuantity;
    private int price;
}
