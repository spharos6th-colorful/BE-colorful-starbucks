package colorful.starbucks.order.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderDetailResponseVo {

    private Long id;
    private Long productCode;
    private String productName;
    private Long sizeId;
    private Long colorId;
    private Integer quantity;
    private Integer price;
    private Boolean carving;
    private String carvingContent;
    private String productDetailThumbnailUrl;

    @Builder
    private OrderDetailResponseVo(Long id,
                                  Long productCode,
                                  String productName,
                                  Long sizeId,
                                  Long colorId,
                                  Integer quantity,
                                  Integer price,
                                  Boolean carving,
                                  String carvingContent,
                                  String productDetailThumbnailUrl) {
        this.id = id;
        this.productCode = productCode;
        this.productName = productName;
        this.sizeId = sizeId;
        this.colorId = colorId;
        this.quantity = quantity;
        this.price = price;
        this.carving = carving;
        this.carvingContent = carvingContent;
        this.productDetailThumbnailUrl = productDetailThumbnailUrl;
    }
}
