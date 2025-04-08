package colorful.starbucks.admin.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductSearchListFilterVo {

    private String query;
    private Long cursor;
    private Integer minPrice;
    private Integer maxPrice;
    private Integer size;
    private String sortBy;
    private Integer page;
    private Integer pageSize;
}
