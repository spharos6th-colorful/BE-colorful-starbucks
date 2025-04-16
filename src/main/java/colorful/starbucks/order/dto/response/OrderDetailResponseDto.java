package colorful.starbucks.order.dto.response;

import colorful.starbucks.order.domain.OrderDetail;
import colorful.starbucks.order.vo.response.OrderDetailResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderDetailResponseDto {

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
    public OrderDetailResponseDto(Long id,
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

    public static OrderDetailResponseDto from(OrderDetail orderDetail) {
        return OrderDetailResponseDto.builder()
                .id(orderDetail.getId())
                .productCode(orderDetail.getProductCode())
                .productName(orderDetail.getProductName())
                .sizeName(orderDetail.getSizeName())
                .colorName(orderDetail.getColorName())
                .quantity(orderDetail.getQuantity())
                .price(orderDetail.getPrice())
                .carving(orderDetail.getCarving())
                .carvingContent(orderDetail.getCarvingContent())
                .productDetailThumbnailUrl(orderDetail.getProductDetailThumbnailUrl())
                .build();
    }

    public OrderDetailResponseVo toVo() {
        return OrderDetailResponseVo.builder()
                .id(this.id)
                .productCode(this.productCode)
                .productName(this.productName)
                .sizeName(this.sizeName)
                .colorName(this.colorName)
                .quantity(this.quantity)
                .price(this.price)
                .carving(this.carving)
                .carvingContent(this.carvingContent)
                .productDetailThumbnailUrl(this.productDetailThumbnailUrl)
                .build();
    }
}
