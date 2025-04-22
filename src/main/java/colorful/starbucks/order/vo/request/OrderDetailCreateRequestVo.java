package colorful.starbucks.order.vo.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderDetailCreateRequestVo {

    private Long productCode;
    private Long productDetailCode;
    private String productName;
    private String sizeName;
    private String colorName;
    private Integer quantity;
    private Integer price;
    private Boolean carving;

    @Builder
    private OrderDetailCreateRequestVo(Long productCode,
                                       Long productDetailCode,
                                       String productName,
                                       String sizeName,
                                       String colorName,
                                       Integer quantity,
                                       Integer price,
                                       Boolean carving) {
        this.productCode = productCode;
        this.productDetailCode = productDetailCode;
        this.productName = productName;
        this.sizeName = sizeName;
        this.colorName = colorName;
        this.quantity = quantity;
        this.price = price;
        this.carving = carving;
    }
}
