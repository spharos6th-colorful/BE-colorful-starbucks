package colorful.starbucks.product.dto.response;

import colorful.starbucks.product.domain.InterestProduct;
import colorful.starbucks.product.vo.response.InterestProductListResponseVo;
import lombok.Builder;
import org.springframework.data.domain.Page;

import java.util.List;

public class InterestProductListResponseDto {

    private int totalPage;
    private long totalElement;
    private List<InterestProductDto> interestProductDtoList;

    @Builder
    private InterestProductListResponseDto(int totalPage, long totalElement, List<InterestProductDto> interestProductDtoList) {
        this.totalPage = totalPage;
        this.totalElement = totalElement;
        this.interestProductDtoList = interestProductDtoList;
    }

    public static InterestProductListResponseDto from(Page<InterestProduct> interestProducts) {
        return InterestProductListResponseDto.builder()
                .totalPage(interestProducts.getTotalPages())
                .totalElement(interestProducts.getTotalElements())
                .interestProductDtoList(
                        interestProducts.getContent()
                                .stream()
                                .map(InterestProductDto::from)
                                .toList()
                )
                .build();
    }

    public InterestProductListResponseVo toVo() {
        return InterestProductListResponseVo.builder()
                .totalPage(totalPage)
                .totalElement(totalElement)
                .interestProductList(interestProductDtoList)
                .build();
    }
}
