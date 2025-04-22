package colorful.starbucks.product.dto.response;

import colorful.starbucks.product.domain.BestProduct;
import colorful.starbucks.product.vo.response.BestProductResponseVos;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class BestProductsResponseDtos {

    private List<BestProductsResponseDto> bestProducts;

    @Builder
    private BestProductsResponseDtos(List<BestProductsResponseDto> bestProducts) {
        this.bestProducts = bestProducts;
    }

    public static BestProductsResponseDtos from(List<BestProduct> bestProducts) {
        return BestProductsResponseDtos.builder()
                .bestProducts(
                        bestProducts.stream()
                                .map(BestProductsResponseDto::from)
                                .toList()
                )
                .build();
    }

    public BestProductResponseVos toVo() {
        return BestProductResponseVos.builder()
                .bestProducts(
                        bestProducts.stream()
                                .map(BestProductsResponseDto::toVo)
                                .toList()
                )
                .build();
    }
}
