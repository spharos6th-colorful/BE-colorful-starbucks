package colorful.starbucks.search.vo.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ElasticsearchRequestVo {

    private String query;
    private String category;
    private Long cursor;
    private Integer minPrice;
    private Integer maxPrice;
    private Integer size;
    private Integer page;
}
