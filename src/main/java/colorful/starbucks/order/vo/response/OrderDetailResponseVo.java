package colorful.starbucks.order.vo.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderDetailResponseVo {

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
    private OrderDetailResponseVo(Long productCode,
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
}
