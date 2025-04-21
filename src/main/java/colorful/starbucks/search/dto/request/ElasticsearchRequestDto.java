package colorful.starbucks.search.dto.request;

import colorful.starbucks.search.vo.request.ElasticsearchRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ElasticsearchRequestDto {

    private String query;
    private String category;
    private Long cursor;
    private Integer minPrice;
    private Integer maxPrice;
    private Integer size;
    private Integer page;

    @Builder
    private ElasticsearchRequestDto(String query,
                                   String category,
                                   Long cursor,
                                   Integer minPrice,
                                   Integer maxPrice,
                                   Integer size,
                                   Integer page) {
        this.query = query;
        this.category = category;
        this.cursor = cursor;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.size = size;
        this.page = page;
    }

    public static ElasticsearchRequestDto from(ElasticsearchRequestVo elasticsearchRequestVo) {
        return ElasticsearchRequestDto.builder()
                .query(elasticsearchRequestVo.getQuery())
                .category(elasticsearchRequestVo.getCategory())
                .size(elasticsearchRequestVo.getSize())
                .cursor(elasticsearchRequestVo.getCursor())
                .maxPrice(elasticsearchRequestVo.getMaxPrice())
                .minPrice(elasticsearchRequestVo.getMinPrice())
                .page(elasticsearchRequestVo.getPage())
                .build();
    }


}
