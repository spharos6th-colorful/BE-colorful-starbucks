package colorful.starbucks.product.vo.request;

import lombok.Getter;

@Getter
public class ProductDetailCodeAndQuantityRequestVo {

    private String productCode;
    private Long sizeId;
    private Long colorId;
}
