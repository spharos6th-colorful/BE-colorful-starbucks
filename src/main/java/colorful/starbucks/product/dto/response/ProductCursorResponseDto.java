package colorful.starbucks.product.dto.response;

import colorful.starbucks.product.vo.response.ProductCursorResponseVo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductCursorResponseDto {

    private Long id;
    private Long productCode;

    public ProductCursorResponseVo toVo(){
        return ProductCursorResponseVo.builder()
                .productCode(productCode)
                .build();
    }
}
