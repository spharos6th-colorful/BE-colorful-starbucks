package colorful.starbucks.order.dto.response;

import colorful.starbucks.order.vo.response.OrderDetailCursorResponseVo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderDetailCursorResponseDto {
    private Long productCode;
    private String productName;
    private String size;
    private String color;
    private int quantity;
    private int price;
    private Boolean carving;
    private String carvingContent;
    private String productDetailThumbnailUrl;

    @Builder
    public OrderDetailCursorResponseDto(Long productCode,
                                          String productName,
                                          String size,
                                          String color,
                                          int quantity,
                                          int price,
                                          Boolean carving,
                                          String carvingContent,
                                          String productDetailThumbnailUrl) {
        this.productCode = productCode;
        this.productName = productName;
        this.size = size;
        this.color = color;
        this.quantity = quantity;
        this.price = price;
        this.carving = carving;
        this.carvingContent = carvingContent;
        this.productDetailThumbnailUrl = productDetailThumbnailUrl;
    }

    public static List<OrderDetailCursorResponseDto> from(List<OrderDetailResponseDto> orderDetailResponseDtos) {
        return orderDetailResponseDtos.stream()
                .map(orderDetailResponseDto -> new OrderDetailCursorResponseDto(
                        orderDetailResponseDto.getProductCode(),
                        orderDetailResponseDto.getProductName(),
                        orderDetailResponseDto.getSize(),
                        orderDetailResponseDto.getColor(),
                        orderDetailResponseDto.getQuantity(),
                        orderDetailResponseDto.getPrice(),
                        orderDetailResponseDto.getCarving(),
                        orderDetailResponseDto.getCarvingContent(),
                        orderDetailResponseDto.getProductDetailThumbnailUrl()))
                .toList();
    }
    public OrderDetailCursorResponseVo toVo(){
        return OrderDetailCursorResponseVo.builder()
                .productCode(this.productCode)
                .productName(this.productName)
                .size(this.size)
                .color(this.color)
                .quantity(this.quantity)
                .price(this.price)
                .carving(this.carving)
                .carvingContent(this.carvingContent)
                .productDetailThumbnailUrl(this.productDetailThumbnailUrl)
                .build();
    }
}
