package colorful.starbucks.order.dto.request;

import colorful.starbucks.order.domain.Order;
import colorful.starbucks.order.domain.OrderDetail;
import colorful.starbucks.order.vo.request.OrderCreateDetailRequestVo;
import colorful.starbucks.product.domain.ProductDetail;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderDetailCreateRequestDto {

    private Long productCode;
    private Long productDetailCode;
    private String productName;
    private Integer quantity;
    private Integer price;
    private Boolean carving;

    @Builder
    private OrderDetailCreateRequestDto(Long productCode,
                                        Long productDetailCode,
                                        String productName,
                                        Integer quantity,
                                        Integer price,
                                        Boolean carving) {
        this.productCode = productCode;
        this.productDetailCode = productDetailCode;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.carving = carving;
    }

    public OrderDetail toEntity(Order order, ProductDetail productDetail) {
        return OrderDetail.builder()
                .order(order)
                .productCode(productCode)
                .productDetailCode(productDetailCode)
                .sizeName(productDetail.getSizeName())
                .colorName(productDetail.getColorName())
                .productName(productName)
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
                .quantity(orderCreateDetailRequestVo.getQuantity())
                .price(orderCreateDetailRequestVo.getPrice())
                .carving(orderCreateDetailRequestVo.getCarving())
                .build();
    }

}
