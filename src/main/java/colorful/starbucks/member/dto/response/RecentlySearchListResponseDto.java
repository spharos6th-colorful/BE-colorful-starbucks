package colorful.starbucks.member.dto.response;

import colorful.starbucks.member.vo.response.RecentlySearchListResponseVo;
import colorful.starbucks.member.vo.response.RecentlyViewProductListResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RecentlySearchListResponseDto {

    private List<RecentlySearchListDto> recentlySearch;

    @Builder
    private RecentlySearchListResponseDto(List<RecentlySearchListDto> recentlySearch) {
        this.recentlySearch = recentlySearch;
    }

    public static RecentlySearchListResponseDto from(List<RecentlySearchListDto> recentlySearch) {
        return RecentlySearchListResponseDto.builder()
                .recentlySearch(recentlySearch)
                .build();
    }

    public RecentlySearchListResponseVo toVo() {
        return RecentlySearchListResponseVo.builder()
                .recentlySearch(
                        recentlySearch.stream()
                                .map(RecentlySearchListDto::toVo)
                                .toList()
                )
                .build();
    }
}
