package colorful.starbucks.search.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategoryCountResponseDto {

    private String topCategoryName;
    private Integer count;

    @Builder
    private CategoryCountResponseDto(String topCategoryName, Integer count) {
        this.topCategoryName = topCategoryName;
        this.count = count;
    }
}
