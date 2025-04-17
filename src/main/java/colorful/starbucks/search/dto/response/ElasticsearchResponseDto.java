package colorful.starbucks.search.dto.response;

import colorful.starbucks.search.vo.response.ElasticsearchResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ElasticsearchResponseDto {
    private Long productCode;
    private Long id;
    private Integer price;
    private String createdAt;

    public ElasticsearchResponseVo toVo(){
        return ElasticsearchResponseVo.builder()
                .productCode(productCode)
                .id(id)
                .price(price)
                .createdAt(createdAt)
                .build();
    }

    @Builder
    private ElasticsearchResponseDto(Long productCode, Long id, Integer price, String createdAt) {
        this.productCode = productCode;
        this.id = id;
        this.price = price;
        this.createdAt = createdAt;
    }
}
