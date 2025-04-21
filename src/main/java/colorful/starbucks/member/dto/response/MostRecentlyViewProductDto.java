package colorful.starbucks.member.dto.response;

import colorful.starbucks.member.vo.response.MostRecentlyViewProductVo;
import colorful.starbucks.product.domain.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MostRecentlyViewProductDto {

    private String productThumbnailUrl;

    @Builder
    private MostRecentlyViewProductDto(String productThumbnailUrl) {
        this.productThumbnailUrl = productThumbnailUrl;
    }

    public static MostRecentlyViewProductDto from(Product product) {
        return MostRecentlyViewProductDto.builder()
                .productThumbnailUrl(product.getProductThumbnailUrl())
                .build();
    }

    public MostRecentlyViewProductVo toVo() {
        return MostRecentlyViewProductVo.builder()
                .productThumbnailUrl(productThumbnailUrl)
                .build();
    }
}
