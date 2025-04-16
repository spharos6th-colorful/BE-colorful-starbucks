package colorful.starbucks.search.dto.response;

import colorful.starbucks.search.vo.response.ElasticsearchResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ElasticsearchResponseDto {
    private Long productCode;

    public ElasticsearchResponseVo toVo(){
        return ElasticsearchResponseVo.builder()
                .productCode(productCode)
                .build();
    }


}
