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
    private String size;
    private String color;
    private int quantity;
    private int price;
    private Boolean carving;

    @Builder
    private OrderDetailRequestDto(String productCode,
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

    public static OrderDetailRequestDto of(OrderDetailRequestVo orderDetailRequestVo) {
        return OrderDetailRequestDto.builder()
                .productCode(orderDetailRequestVo.getProductCode())
                .productName(orderDetailRequestVo.getProductName())
                .size(orderDetailRequestVo.getSize())
                .color(orderDetailRequestVo.getColor())
                .quantity(orderDetailRequestVo.getQuantity())
                .price(orderDetailRequestVo.getPrice())
                .carving(orderDetailRequestVo.getCarving())
                .build();
    }


}
