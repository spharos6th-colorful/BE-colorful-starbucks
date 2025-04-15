package colorful.starbucks.search.dto.response;

import colorful.starbucks.search.vo.response.AutoSearchListResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class AutoSearchListResponseDto {

    private List<AutoSearchResponseDto> autoSearchList;

    @Builder
    private AutoSearchListResponseDto(List<AutoSearchResponseDto> autoSearchList) {
        this.autoSearchList = autoSearchList;
    }

    public AutoSearchListResponseVo toVo() {
        return AutoSearchListResponseVo.builder()
                .autoSearchList(
                        autoSearchList.stream()
                                .map(AutoSearchResponseDto::toVo)
                                .toList()
                )
                .build();
    }
}
