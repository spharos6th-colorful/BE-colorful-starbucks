package colorful.starbucks.product.vo.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductDetailCodeAndQuantityRequestVo {

    private Long productDetailCode;
    private Long productCode;
    private Long sizeId;
    private Long colorId;
    private Integer quantity;
}
