package colorful.starbucks.order.dto.response;

import colorful.starbucks.order.domain.OrderDetail;
import colorful.starbucks.order.vo.response.OrderDetailCursorResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class OrderDetailCursorResponseDto {

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
    public OrderDetailCursorResponseDto(Long id,
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

    public static OrderDetailCursorResponseDto from(OrderDetail orderDetail) {
        return OrderDetailCursorResponseDto.builder()
                .id(orderDetail.getId())
                .productCode(orderDetail.getProductCode())
                .productName(orderDetail.getProductName())
                .sizeId(orderDetail.getSizeId())
                .colorId(orderDetail.getColorId())
                .quantity(orderDetail.getQuantity())
                .price(orderDetail.getPrice())
                .carving(orderDetail.getCarving())
                .carvingContent(orderDetail.getCarvingContent())
                .productDetailThumbnailUrl(orderDetail.getProductDetailThumbnailUrl())
                .build();
    }

    public OrderDetailCursorResponseVo toVo() {
        return OrderDetailCursorResponseVo.builder()
                .id(this.id)
                .productCode(this.productCode)
                .productName(this.productName)
                .sizeId(this.sizeId)
                .colorId(this.colorId)
                .quantity(this.quantity)
                .price(this.price)
                .carving(this.carving)
                .carvingContent(this.carvingContent)
                .productDetailThumbnailUrl(this.productDetailThumbnailUrl)
                .build();
    }
}
