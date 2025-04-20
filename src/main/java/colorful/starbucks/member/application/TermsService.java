package colorful.starbucks.member.application;

import colorful.starbucks.member.domain.TermsCategory;
import colorful.starbucks.member.dto.request.TermsCreateRequestDto;
import colorful.starbucks.member.dto.response.TermsResponseDto;

import java.util.List;

public interface TermsService {


     void createTerms(TermsCreateRequestDto termsCreateRequestDto);

     List<TermsResponseDto> getTerms();

     List<TermsResponseDto> getTermsByCategory(TermsCategory termsCategory);


}
