package colorful.starbucks.product.dto.response;

import colorful.starbucks.product.domain.InterestProduct;
import lombok.Builder;
import lombok.Getter;

@Getter
public class InterestProductDto {
    private String productCode;
    private String productName;
    private int price;
    private String productThumbnailUrl;

    @Builder
    private InterestProductDto(String productCode, String productName, int price, String productThumbnailUrl) {
        this.productCode = productCode;
        this.productName = productName;
        this.price = price;
        this.productThumbnailUrl = productThumbnailUrl;
    }

    public static InterestProductDto from(InterestProduct interestProduct) {
        return InterestProductDto.builder()
                .productCode(interestProduct.getProductCode())
                .productName(interestProduct.getProductName())
                .price(interestProduct.getPrice())
                .productThumbnailUrl(interestProduct.getProductThumbnailUrl())
                .build();
    }
}
