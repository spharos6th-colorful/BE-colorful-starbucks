package colorful.starbucks.order.vo.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderCreateDetailRequestVo {

    private Long productCode;
    private Long productDetailCode;
    private String productName;
    private String size;
    private String color;
    private int quantity;
    private int price;
    private Boolean carving;

    @Builder
    private OrderCreateDetailRequestVo(Long productCode,
                                       Long productDetailCode,
                                       String productName,
                                       String size,
                                       String color,
                                       int quantity,
                                       int price,
                                       Boolean carving) {
        this.productCode = productCode;
        this.productDetailCode = productDetailCode;
        this.productName = productName;
        this.size = size;
        this.color = color;
        this.quantity = quantity;
        this.price = price;
        this.carving = carving;
    }
}
