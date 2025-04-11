package colorful.starbucks.product.vo.request;

import lombok.Getter;

@Getter
public class ProductDetailCreateRequestVo {

    private Long productCode;
    private Long sizeId;
    private String sizeName;
    private Long colorId;
    private String colorName;
    private Integer inventoryQuantity;
    private Integer price;
    private Integer discountPrice;
}
