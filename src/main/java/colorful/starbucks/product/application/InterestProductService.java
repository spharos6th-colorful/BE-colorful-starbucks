package colorful.starbucks.product.application;

import colorful.starbucks.product.dto.InterestProductDto;
import colorful.starbucks.product.dto.request.InterestProductAddRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InterestProductService {

    void addInterestProduct(InterestProductAddRequestDto interestProductAddRequestDto);

    Page<InterestProductDto> getInterestProductList(String memberUuid, Pageable pageable);

    void removeInterestProduct(Long interestProductId, String memberUuid);
}
