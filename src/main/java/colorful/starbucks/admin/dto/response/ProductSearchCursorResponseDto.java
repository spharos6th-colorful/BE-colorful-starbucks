package colorful.starbucks.admin.dto.response;

import colorful.starbucks.admin.vo.response.ProductSearchCursorResponseVo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductSearchCursorResponseDto {

    private Long id;
    private Long productCode;

    public ProductSearchCursorResponseVo toVo(){
        return ProductSearchCursorResponseVo.builder()
                .id(id)
                .productCode(productCode)
                .build();
    }
}
