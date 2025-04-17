package colorful.starbucks.search.dto.request;

import colorful.starbucks.search.vo.request.ElasticsearchRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ElasticsearchRequestDto {

    private String query;
    private Long cursor;
    private Integer minPrice;
    private Integer maxPrice;
    private Integer size;
    private String sortBy;
    private Integer page;

    @Builder
    private ElasticsearchRequestDto(String query,
                                   Long cursor,
                                   Integer minPrice,
                                   Integer maxPrice,
                                   Integer size,
                                   String sortBy,
                                   Integer page) {
        this.query = query;
        this.cursor = cursor;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.size = size;
        this.sortBy = sortBy;
        this.page = page;
    }

    public static ElasticsearchRequestDto from(ElasticsearchRequestVo elasticsearchRequestVo) {
        return ElasticsearchRequestDto.builder()
                .query(elasticsearchRequestVo.getQuery())
                .size(elasticsearchRequestVo.getSize())
                .cursor(elasticsearchRequestVo.getCursor())
                .maxPrice(elasticsearchRequestVo.getMaxPrice())
                .minPrice(elasticsearchRequestVo.getMinPrice())
                .sortBy(elasticsearchRequestVo.getSortBy())
                .build();
    }


}
