package colorful.starbucks.member.dto.request;

import colorful.starbucks.member.domain.InterestProduct;
import colorful.starbucks.member.vo.request.InterestProductAddRequestVo;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InterestProductAddRequestDto {

    private String memberUuid;
    private Long productCode;

    @Builder
    private InterestProductAddRequestDto(String memberUuid,
                                         Long productCode) {
        this.memberUuid = memberUuid;
        this.productCode = productCode;
    }

    public static InterestProductAddRequestDto of(InterestProductAddRequestVo interestProductAddRequestVo,
                                                  String memberUuid) {

        return InterestProductAddRequestDto.builder()
                .memberUuid(memberUuid)
                .productCode(interestProductAddRequestVo.getProductCode())
                .build();
    }

    public InterestProduct toEntity() {
        return InterestProduct.builder()
                .memberUuid(memberUuid)
                .productCode(productCode)
                .build();
    }
}
