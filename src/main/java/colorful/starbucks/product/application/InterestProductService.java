package colorful.starbucks.product.application;

import colorful.starbucks.product.dto.request.InterestProductAddRequestDto;
import colorful.starbucks.product.dto.response.InterestProductListResponseDto;
import org.springframework.data.domain.Pageable;

public interface InterestProductService {

    void addInterestProduct(InterestProductAddRequestDto interestProductAddRequestDto);

    void removeInterestProduct(Long interestProductId);

    InterestProductListResponseDto getInterestProductList(String memberUuid, Pageable pageable);
}
