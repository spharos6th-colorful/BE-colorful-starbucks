package colorful.starbucks.member.dto.response;

import colorful.starbucks.member.vo.response.RecentlyViewProductResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RecentlyViewProductResponseDto {

    private Long productCode;

    @Builder
    private RecentlyViewProductResponseDto(Long productCode) {
        this.productCode = productCode;
    }

    public static RecentlyViewProductResponseDto from(Long productCode) {
        return RecentlyViewProductResponseDto.builder()
                .productCode(productCode)
                .build();
    }

    public RecentlyViewProductResponseVo toVo() {
        return RecentlyViewProductResponseVo.builder()
                .productCode(productCode)
                .build();
    }
}
