package colorful.starbucks.member.application;

import colorful.starbucks.member.dto.InterestProductDto;
import colorful.starbucks.member.dto.request.InterestProductAddRequestDto;
import colorful.starbucks.member.dto.request.InterestProductRemoveDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InterestProductService {

    void addInterestProduct(InterestProductAddRequestDto interestProductAddRequestDto);

    Page<InterestProductDto> getInterestProductList(String memberUuid, Pageable pageable);

    void removeInterestProduct(InterestProductRemoveDto interestProductRemoveDto);
}
