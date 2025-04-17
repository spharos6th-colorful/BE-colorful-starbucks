package colorful.starbucks.member.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InterestProductRemoveDto {

    private String memberUuid;
    private Long productCode;

    @Builder
    private InterestProductRemoveDto(String memberUuid, Long productCode) {
        this.memberUuid = memberUuid;
        this.productCode = productCode;
    }

    public static InterestProductRemoveDto of(String memberUuid, Long productCode) {
        return InterestProductRemoveDto.builder()
                .memberUuid(memberUuid)
                .productCode(productCode)
                .build();
    }
}
