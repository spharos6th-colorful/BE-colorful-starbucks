package colorful.starbucks.order.dto.in;

import colorful.starbucks.order.domain.Order;
import colorful.starbucks.order.domain.OrderDetail;
import colorful.starbucks.order.vo.in.OrderDetailRequestVo;
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

    public OrderDetail toEntity(Order order) {
        return OrderDetail.builder()
                .order(order)
                .productCode(productCode)
                .productName(productName)
                .size(size)
                .color(color)
                .quantity(quantity)
                .price(price)
                .carving(carving)
                .build();
    }

    public static OrderDetailRequestDto of(OrderDetailRequestVo vo) {
        return OrderDetailRequestDto.builder()
                .productCode(vo.getProductCode())
                .productName(vo.getProductName())
                .size(vo.getSize())
                .color(vo.getColor())
                .quantity(vo.getQuantity())
                .price(vo.getPrice())
                .carving(vo.getCarving())
                .build();
    }


}
