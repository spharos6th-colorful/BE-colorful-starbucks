package colorful.starbucks.product.vo.response;

import colorful.starbucks.product.dto.response.InterestProductDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class InterestProductListResponseVo {

    private int totalPages;
    private long totalElements;
    private List<InterestProductDto> interestProductList;

    @Builder
    private InterestProductListResponseVo(int totalPages, long totalElements, List<InterestProductDto> interestProductList) {
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.interestProductList = interestProductList;
    }
}
