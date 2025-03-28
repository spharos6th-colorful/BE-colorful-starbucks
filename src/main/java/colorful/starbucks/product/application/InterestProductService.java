package colorful.starbucks.product.application;

import colorful.starbucks.product.dto.request.InterestProductCreateRequestDto;
import colorful.starbucks.product.dto.response.InterestProductCreateResponseDto;
import colorful.starbucks.product.dto.response.InterestProductListResponseDto;
import org.springframework.data.domain.Pageable;

public interface InterestProductService {

    InterestProductCreateResponseDto createInterestProduct(
            InterestProductCreateRequestDto interestProductCreateRequestDto
    );

    void removeInterestProduct(String memberUuid, String productCode);

    InterestProductListResponseDto getInterestProductList(String memberUuid, Pageable pageable);
}
