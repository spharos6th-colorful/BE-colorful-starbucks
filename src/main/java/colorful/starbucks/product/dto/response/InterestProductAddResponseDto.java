package colorful.starbucks.product.dto.response;

import colorful.starbucks.product.domain.InterestProduct;
import colorful.starbucks.product.vo.response.InterestProductAddResponseVo;
import lombok.Builder;

public class InterestProductAddResponseDto {

    private String productCode;
    private String productName;
    private int price;
    private String productThumbnailUrl;

    @Builder
    private InterestProductAddResponseDto(
            String productCode,
            String productName,
            int price,
            String productThumbnailUrl) {
        this.productCode = productCode;
        this.productName = productName;
        this.price = price;
        this.productThumbnailUrl = productThumbnailUrl;
    }

    public static InterestProductAddResponseDto from(InterestProduct interestProduct) {
        return InterestProductAddResponseDto.builder()
                .productCode(interestProduct.getProductCode())
                .productName(interestProduct.getProductName())
                .price(interestProduct.getPrice())
                .productThumbnailUrl(interestProduct.getProductThumbnailUrl())
                .build();
    }

    public InterestProductAddResponseVo toVo() {
        return InterestProductAddResponseVo.builder()
                .productCode(productCode)
                .productName(productName)
                .price(price)
                .productThumbnailUrl(productThumbnailUrl)
                .build();
    }
}
