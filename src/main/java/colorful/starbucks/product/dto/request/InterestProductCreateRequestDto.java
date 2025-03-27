package colorful.starbucks.product.dto.request;

import colorful.starbucks.product.domain.InterestProduct;
import colorful.starbucks.product.vo.request.InterestProductCreateRequestVo;
import lombok.Builder;

public class InterestProductCreateRequestDto {

    private String memberUuid;
    private String productCode;
    private int price;
    private String productThumbnailUrl;

    @Builder
    private InterestProductCreateRequestDto(
            String memberUuid,
            String productCode,
            int price,
            String productThumbnailUrl) {
        this.memberUuid = memberUuid;
        this.productCode = productCode;
        this.price = price;
        this.productThumbnailUrl = productThumbnailUrl;
    }

    public static InterestProductCreateRequestDto from(
            InterestProductCreateRequestVo interestProductCreateRequestVo,
            String memberUuid) {

        return InterestProductCreateRequestDto.builder()
                .memberUuid(memberUuid)
                .productCode(interestProductCreateRequestVo.getProductCode())
                .price(interestProductCreateRequestVo.getPrice())
                .productThumbnailUrl(interestProductCreateRequestVo.getProductThumbnailUrl())
                .build();
    }

    public InterestProduct toEntity() {
        return InterestProduct.builder()
                .memberUuid(memberUuid)
                .productCode(productCode)
                .price(price)
                .productThumbnailUrl(productThumbnailUrl)
                .build();
    }
}
