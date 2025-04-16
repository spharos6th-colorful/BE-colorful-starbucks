package colorful.starbucks.order.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderDetailCursorResponseVo {

    private Long id;
    private Long productCode;
    private String productName;
    private String sizeName;
    private String colorName;
    private Integer quantity;
    private Integer price;
    private Boolean carving;
    private String carvingContent;
    private String productDetailThumbnailUrl;

    @Builder
    private OrderDetailCursorResponseVo(Long id,
                                        Long productCode,
                                        String productName,
                                        String sizeName,
                                        String colorName,
                                        Integer quantity,
                                        Integer price,
                                        Boolean carving,
                                        String carvingContent,
                                        String productDetailThumbnailUrl) {
        this.id = id;
        this.productCode = productCode;
        this.productName = productName;
        this.sizeName = sizeName;
        this.colorName = colorName;
        this.quantity = quantity;
        this.price = price;
        this.carving = carving;
        this.carvingContent = carvingContent;
        this.productDetailThumbnailUrl = productDetailThumbnailUrl;
    }
}
