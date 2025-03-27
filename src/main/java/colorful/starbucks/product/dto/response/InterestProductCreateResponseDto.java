package colorful.starbucks.product.dto.response;

import colorful.starbucks.product.domain.InterestProduct;
import colorful.starbucks.product.vo.response.InterestProductCreateResponseVo;
import lombok.Builder;

public class InterestProductCreateResponseDto {

    private String productCode;
    private String productName;
    private int price;
    private String productThumbnailUrl;

    @Builder
    private InterestProductCreateResponseDto(
            String productCode,
            String productName,
            int price,
            String productThumbnailUrl) {
        this.productCode = productCode;
        this.productName = productName;
        this.price = price;
        this.productThumbnailUrl = productThumbnailUrl;
    }

    public static InterestProductCreateResponseDto from(InterestProduct interestProduct) {
        return InterestProductCreateResponseDto.builder()
                .productCode(interestProduct.getProductCode())
                .productName(interestProduct.getProductName())
                .price(interestProduct.getPrice())
                .productThumbnailUrl(interestProduct.getProductThumbnailUrl())
                .build();
    }

    public InterestProductCreateResponseVo toVo() {
        return InterestProductCreateResponseVo.builder()
                .productCode(productCode)
                .productName(productName)
                .price(price)
                .productThumbnailUrl(productThumbnailUrl)
                .build();
    }
}
