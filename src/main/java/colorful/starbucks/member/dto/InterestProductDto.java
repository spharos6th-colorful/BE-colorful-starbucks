package colorful.starbucks.member.dto;

import colorful.starbucks.member.domain.InterestProduct;
import colorful.starbucks.member.vo.InterestProductVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InterestProductDto {

    private Long productCode;

    @Builder
    private InterestProductDto(Long productCode) {
        this.productCode = productCode;
    }

    public static InterestProductDto from(InterestProduct interestProduct) {
        return InterestProductDto.builder()
                .productCode(interestProduct.getProductCode())
                .build();
    }

    public InterestProductVo toVo() {
        return InterestProductVo.builder()
                .productCode(productCode)
                .build();
    }
}
