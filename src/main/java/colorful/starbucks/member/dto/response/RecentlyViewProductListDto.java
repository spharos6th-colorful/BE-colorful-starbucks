package colorful.starbucks.member.dto.response;

import colorful.starbucks.member.vo.response.RecentlyViewProductListVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
public class RecentlyViewProductListDto {

    private LocalDate viewedAt;
    private List<RecentlyViewProductResponseDto> recentlyViewProducts;

    @Builder
    private RecentlyViewProductListDto(LocalDate viewedAt, List<RecentlyViewProductResponseDto> recentlyViewProducts) {
        this.viewedAt = viewedAt;
        this.recentlyViewProducts = recentlyViewProducts;
    }

    public static RecentlyViewProductListDto of(LocalDate viewedAt, List<Long> productCodes) {
        return RecentlyViewProductListDto.builder()
                .viewedAt(viewedAt)
                .recentlyViewProducts(productCodes.stream()
                        .map(RecentlyViewProductResponseDto::from)
                        .toList())
                .build();
    }

    public RecentlyViewProductListVo toVo() {
        return RecentlyViewProductListVo.builder()
                .viewedAt(viewedAt)
                .recentlyViewProducts(recentlyViewProducts.stream()
                        .map(RecentlyViewProductResponseDto::toVo)
                        .toList())
                .build();
    }
}
