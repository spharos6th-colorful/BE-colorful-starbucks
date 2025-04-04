package colorful.starbucks.order.dto.in;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderDetailRequestDto {

    private String productCode;

    private String productName;

    private int size;

    private String color;

    private int quantity;

    private int price;

    private Boolean carving;

    @Builder
    private OrderDetailRequestDto(String productCode,
                                  String productName,
                                  int size,
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
