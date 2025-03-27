package colorful.starbucks.product.vo.response;

import colorful.starbucks.product.dto.response.InterestProductDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class InterestProductListResponseVo {

    private int totalPage;
    private long totalElement;
    private List<InterestProductDto> interestProductList;

    @Builder
    private InterestProductListResponseVo(int totalPage, long totalElement, List<InterestProductDto> interestProductList) {
        this.totalPage = totalPage;
        this.totalElement = totalElement;
        this.interestProductList = interestProductList;
    }
}
