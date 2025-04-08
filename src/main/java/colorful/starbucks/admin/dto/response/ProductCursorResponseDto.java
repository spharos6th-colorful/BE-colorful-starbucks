package colorful.starbucks.admin.dto.response;

import colorful.starbucks.admin.vo.response.ProductCursorResponseVo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductCursorResponseDto {

    private Long id;
    private Long productCode;

    public ProductCursorResponseVo toVo(){
        return ProductCursorResponseVo.builder()
                .id(id)
                .productCode(productCode)
                .build();
    }
}
