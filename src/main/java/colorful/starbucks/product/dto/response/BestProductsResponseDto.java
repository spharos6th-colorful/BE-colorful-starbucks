package colorful.starbucks.product.dto.response;

import colorful.starbucks.product.domain.BestProduct;
import colorful.starbucks.product.vo.response.BestProductResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BestProductsResponseDto {

    private Long productCode;
    private Integer ranking;

    @Builder
    private BestProductsResponseDto(Long productCode, Integer ranking) {
        this.productCode = productCode;
        this.ranking = ranking;
    }

    public static BestProductsResponseDto from(BestProduct bestProduct) {
        return BestProductsResponseDto.builder()
                .productCode(bestProduct.getProductCode())
                .ranking(bestProduct.getRanking())
                .build();
    }

    public BestProductResponseVo toVo() {
        return BestProductResponseVo.builder()
                .productCode(productCode)
                .ranking(ranking)
                .build();
    }
}
