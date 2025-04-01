package colorful.starbucks.product.dto.request;

import colorful.starbucks.product.domain.InterestProduct;
import colorful.starbucks.product.vo.request.InterestProductCreateRequestVo;
import lombok.Builder;

public class InterestProductAddRequestDto {

    private String memberUuid;
    private String productCode;
    private String productName;
    private int price;
    private String productThumbnailUrl;

    @Builder
    private InterestProductAddRequestDto(
            String memberUuid,
            String productCode,
            String productName,
            int price,
            String productThumbnailUrl) {
        this.memberUuid = memberUuid;
        this.productCode = productCode;
        this.productName = productName;
        this.price = price;
        this.productThumbnailUrl = productThumbnailUrl;
    }

    public static InterestProductAddRequestDto from(
            InterestProductCreateRequestVo interestProductCreateRequestVo,
            String memberUuid) {

        return InterestProductAddRequestDto.builder()
                .memberUuid(memberUuid)
                .productCode(interestProductCreateRequestVo.getProductCode())
                .productName(interestProductCreateRequestVo.getProductName())
                .price(interestProductCreateRequestVo.getPrice())
                .productThumbnailUrl(interestProductCreateRequestVo.getProductThumbnailUrl())
                .build();
    }

    public InterestProduct toEntity() {
        return InterestProduct.builder()
                .memberUuid(memberUuid)
                .productCode(productCode)
                .productName(productName)
                .price(price)
                .productThumbnailUrl(productThumbnailUrl)
                .build();
    }
}
