package colorful.starbucks.search.dto.response;

import colorful.starbucks.search.vo.response.AutoSearchResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AutoSearchResponseDto {

    private String productName;

    @Builder
    private AutoSearchResponseDto(String productName) {
        this.productName = productName;
    }

    public AutoSearchResponseVo toVo() {
        return AutoSearchResponseVo.builder()
                .productName(productName)
                .build();
    }



}
