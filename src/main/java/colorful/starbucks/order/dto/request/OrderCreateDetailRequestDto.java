package colorful.starbucks.order.dto.request;

import colorful.starbucks.order.domain.Order;
import colorful.starbucks.order.domain.OrderDetail;
import colorful.starbucks.order.vo.request.OrderCreateDetailRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderCreateDetailRequestDto {

    private Long productCode;
    private String productName;
    private String size;
    private String color;
    private int quantity;
    private int price;
    private Boolean carving;

    @Builder
    private OrderCreateDetailRequestDto(Long productCode,
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
                .categoryName("")
                .productDetailCode("")
                .build();
    }

    public static OrderCreateDetailRequestDto of(OrderCreateDetailRequestVo orderCreateDetailRequestVo) {
        return OrderCreateDetailRequestDto.builder()
                .productCode(orderCreateDetailRequestVo.getProductCode())
                .productName(orderCreateDetailRequestVo.getProductName())
                .size(orderCreateDetailRequestVo.getSize())
                .color(orderCreateDetailRequestVo.getColor())
                .quantity(orderCreateDetailRequestVo.getQuantity())
                .price(orderCreateDetailRequestVo.getPrice())
                .carving(orderCreateDetailRequestVo.getCarving())
                .build();
    }


}
