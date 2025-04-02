package colorful.starbucks.product.dto.response;

import colorful.starbucks.product.domain.InterestProduct;
import colorful.starbucks.product.dto.InterestProductDto;
import colorful.starbucks.product.vo.response.InterestProductListResponseVo;
import lombok.Builder;
import org.springframework.data.domain.Page;

import java.util.List;

public class InterestProductListResponseDto {

    private int totalPages;
    private long totalElements;
    private List<InterestProductDto> interestProductDtos;

    @Builder
    private InterestProductListResponseDto(int totalPages,
                                           long totalElements,
                                           List<InterestProductDto> interestProductDtos) {
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.interestProductDtos = interestProductDtos;
    }

    public static InterestProductListResponseDto from(Page<InterestProduct> interestProducts) {

        return InterestProductListResponseDto.builder()
                .totalPages(interestProducts.getTotalPages())
                .totalElements(interestProducts.getTotalElements())
                .interestProductDtos(
                        interestProducts.getContent()
                                .stream()
                                .map(InterestProductDto::from)
                                .toList()
                )
                .build();
    }

    public InterestProductListResponseVo toVo() {
        return InterestProductListResponseVo.builder()
                .totalPages(totalPages)
                .totalElements(totalElements)
                .content(interestProductDtos.stream()
                        .map(InterestProductDto::toVo)
                        .toList())
                .build();
    }
}
