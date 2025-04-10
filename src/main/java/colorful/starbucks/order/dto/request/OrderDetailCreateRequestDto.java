package colorful.starbucks.order.dto.request;

import colorful.starbucks.order.domain.Order;
import colorful.starbucks.order.domain.OrderDetail;
import colorful.starbucks.order.vo.request.OrderCreateDetailRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderDetailCreateRequestDto {

    private Long productCode;
    private Long productDetailCode;
    private String productName;
    private String size;
    private String color;
    private int quantity;
    private int price;
    private Boolean carving;

    @Builder
    private OrderDetailCreateRequestDto(Long productCode,
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

    public OrderDetail toEntity(Order order) {
        return OrderDetail.builder()
                .order(order)
                .productCode(productCode)
                .productDetailCode(productDetailCode)
                .productName(productName)
                .size(size)
                .color(color)
                .quantity(quantity)
                .price(price)
                .carving(carving)
                .categoryName("")
                .build();
    }

    public static OrderDetailCreateRequestDto of(OrderCreateDetailRequestVo orderCreateDetailRequestVo) {
        return OrderDetailCreateRequestDto.builder()
                .productCode(orderCreateDetailRequestVo.getProductCode())
                .productDetailCode(orderCreateDetailRequestVo.getProductDetailCode())
                .productName(orderCreateDetailRequestVo.getProductName())
                .size(orderCreateDetailRequestVo.getSize())
                .color(orderCreateDetailRequestVo.getColor())
                .quantity(orderCreateDetailRequestVo.getQuantity())
                .price(orderCreateDetailRequestVo.getPrice())
                .carving(orderCreateDetailRequestVo.getCarving())
                .build();
    }


}
