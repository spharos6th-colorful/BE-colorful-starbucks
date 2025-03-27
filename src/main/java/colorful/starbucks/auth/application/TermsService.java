package colorful.starbucks.auth.application;

import colorful.starbucks.auth.dto.request.TermsCreateRequestDto;
import colorful.starbucks.auth.dto.response.TermsResponseDto;

import java.util.List;

public interface TermsService {


     void createTerms(TermsCreateRequestDto termsCreateRequestDto);

     List<TermsResponseDto> getTerms();
}
