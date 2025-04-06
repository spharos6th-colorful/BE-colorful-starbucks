package colorful.starbucks.order.dto.response;

import colorful.starbucks.order.vo.response.OrderDetailResponseVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderDetailResponseDto {

    private String productCode;
    private String productName;
    private String size;
    private String color;
    private int quantity;
    private int price;
    private Boolean carving;
    private String carvingContent;
    private String productDetailThumbnailUrl;

    @Builder
    private OrderDetailResponseDto(String productCode,
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

    public OrderDetailResponseVo toVo(){
        return OrderDetailResponseVo.builder()
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
