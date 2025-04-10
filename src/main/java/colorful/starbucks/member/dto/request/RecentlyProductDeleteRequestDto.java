package colorful.starbucks.member.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RecentlyProductDeleteRequestDto {

    private String memberUuid;
    private Long productCode;

    @Builder
    private RecentlyProductDeleteRequestDto(String memberUuid, Long productCode) {
        this.memberUuid = memberUuid;
        this.productCode = productCode;
    }

    public static RecentlyProductDeleteRequestDto of(String memberUuid, Long productCode) {
        return RecentlyProductDeleteRequestDto.builder()
                .memberUuid(memberUuid)
                .productCode(productCode)
                .build();
    }
}
