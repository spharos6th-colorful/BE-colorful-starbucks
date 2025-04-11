package colorful.starbucks.product.dto;

import colorful.starbucks.product.domain.InterestProduct;
import colorful.starbucks.product.vo.InterestProductVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InterestProductDto {

    private Long interestProductId;
    private Long productCode;
    private String productName;
    private Integer price;
    private String productThumbnailUrl;

    @Builder
    private InterestProductDto(Long interestProductId,
                               Long productCode,
                               String productName,
                               Integer price,
                               String productThumbnailUrl) {
        this.interestProductId = interestProductId;
        this.productCode = productCode;
        this.productName = productName;
        this.price = price;
        this.productThumbnailUrl = productThumbnailUrl;
    }

    public static InterestProductDto from(InterestProduct interestProduct) {
        return InterestProductDto.builder()
                .interestProductId(interestProduct.getId())
                .productCode(interestProduct.getProductCode())
                .productName(interestProduct.getProductName())
                .price(interestProduct.getPrice())
                .productThumbnailUrl(interestProduct.getProductThumbnailUrl())
                .build();
    }

    public InterestProductVo toVo() {
        return InterestProductVo.builder()
                .interestProductId(interestProductId)
                .productCode(productCode)
                .productName(productName)
                .price(price)
                .productThumbnailUrl(productThumbnailUrl)
                .build();
    }
}
