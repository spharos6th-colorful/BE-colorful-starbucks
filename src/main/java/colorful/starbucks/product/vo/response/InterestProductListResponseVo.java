package colorful.starbucks.product.vo.response;

import colorful.starbucks.product.vo.InterestProductVo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class InterestProductListResponseVo {

    private List<InterestProductVo> content;
    private int totalPages;
    private long totalElements;

    @Builder

    public InterestProductListResponseVo(List<InterestProductVo> content,
                                         int totalPages,
                                         long totalElements) {
        this.content = content;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }
}
