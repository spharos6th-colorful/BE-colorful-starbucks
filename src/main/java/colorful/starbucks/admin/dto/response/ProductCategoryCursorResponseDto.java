package colorful.starbucks.admin.dto.response;

import colorful.starbucks.admin.vo.response.ProductCategoryCursorResponseVo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductCategoryCursorResponseDto {

    private Long id;
    private Long productCode;

    public ProductCategoryCursorResponseVo toVo() {
        return ProductCategoryCursorResponseVo.builder()
                .id(id)
                .productCode(productCode)
                .build();
    }
}
