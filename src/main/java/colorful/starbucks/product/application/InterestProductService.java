package colorful.starbucks.product.application;

import colorful.starbucks.product.dto.request.InterestProductCreateRequestDto;
import colorful.starbucks.product.dto.response.InterestProductCreateResponseDto;

public interface InterestProductService {

    InterestProductCreateResponseDto createInterestProduct(
            InterestProductCreateRequestDto interestProductCreateRequestDto
    );
}
