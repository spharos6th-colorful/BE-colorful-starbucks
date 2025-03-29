package colorful.starbucks.cart.dto.response;

import colorful.starbucks.cart.vo.response.CartProductOptionEditResponseVo;
import colorful.starbucks.product.domain.ProductDetail;
import lombok.Builder;

public class CartProductOptionEditResponseDto {

    private Long colorId;
    private Long sizeId;

    @Builder
    public CartProductOptionEditResponseDto(Long colorId, Long sizeId) {
        this.colorId = colorId;
        this.sizeId = sizeId;
    }

    public CartProductOptionEditResponseVo toVo(){
        return CartProductOptionEditResponseVo.builder()
                        .colorId(colorId)
                        .sizeId(sizeId)
                        .build();
    }

    public static CartProductOptionEditResponseDto from(ProductDetail productDetail) {
        return CartProductOptionEditResponseDto.builder()
                .colorId(productDetail.getColorId())
                .sizeId(productDetail.getSizeId())
                .build();
    }
}
