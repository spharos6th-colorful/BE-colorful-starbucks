package colorful.starbucks.member.dto.request;

import colorful.starbucks.member.domain.InterestProduct;
import colorful.starbucks.member.vo.request.InterestProductAddRequestVo;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InterestProductAddRequestDto {

    private String memberUuid;
    private Long productCode;
    private String productName;
    private Integer price;
    private String productThumbnailUrl;

    @Builder
    private InterestProductAddRequestDto(String memberUuid,
                                         Long productCode,
                                         String productName,
                                         Integer price,
                                         String productThumbnailUrl) {
        this.memberUuid = memberUuid;
        this.productCode = productCode;
        this.productName = productName;
        this.price = price;
        this.productThumbnailUrl = productThumbnailUrl;
    }

    public static InterestProductAddRequestDto of(InterestProductAddRequestVo interestProductAddRequestVo,
                                                  String memberUuid) {

        return InterestProductAddRequestDto.builder()
                .memberUuid(memberUuid)
                .productCode(interestProductAddRequestVo.getProductCode())
                .productName(interestProductAddRequestVo.getProductName())
                .price(interestProductAddRequestVo.getPrice())
                .productThumbnailUrl(interestProductAddRequestVo.getProductThumbnailUrl())
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
