package colorful.starbucks.order.vo.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderDetailRequestVo {

    private Long productCode;
    private String productName;
    private String size;
    private String color;
    private int quantity;
    private int price;
    private Boolean carving;

    @Builder
    private OrderDetailRequestVo(Long productCode,
                                String productName,
                                String size,
                                String color,
                                int quantity,
                                int price,
                                Boolean carving) {
        this.productCode = productCode;
        this.productName = productName;
        this.size = size;
        this.color = color;
        this.quantity = quantity;
        this.price = price;
        this.carving = carving;
    }
}
