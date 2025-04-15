package colorful.starbucks.order.vo.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderCreateDetailRequestVo {

    private Long id;
    private Long productCode;
    private Long productDetailCode;
    private String productName;
    private Long sizeId;
    private Long colorId;
    private Integer quantity;
    private Integer price;
    private Boolean carving;

    @Builder
    private OrderCreateDetailRequestVo(Long id,
                                       Long productCode,
                                       Long productDetailCode,
                                       String productName,
                                       Long sizeId,
                                       Long colorId,
                                       Integer quantity,
                                       Integer price,
                                       Boolean carving) {
        this.id = id;
        this.productCode = productCode;
        this.productDetailCode = productDetailCode;
        this.productName = productName;
        this.sizeId = sizeId;
        this.colorId = colorId;
        this.quantity = quantity;
        this.price = price;
        this.carving = carving;
    }
}
