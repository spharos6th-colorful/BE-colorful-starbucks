package colorful.starbucks.product.dto;

import colorful.starbucks.product.domain.InterestProduct;
import lombok.Builder;
import lombok.Getter;

@Getter
public class InterestProductDto {

    private Long interestProductId;
    private String productCode;
    private String productName;
    private int price;
    private String productThumbnailUrl;

    @Builder
    private InterestProductDto(Long interestProductId,
                               String productCode,
                               String productName,
                               int price,
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
}
