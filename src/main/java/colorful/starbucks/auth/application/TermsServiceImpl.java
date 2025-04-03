package colorful.starbucks.auth.application;

import colorful.starbucks.auth.domain.Terms;
import colorful.starbucks.auth.dto.request.TermsCreateRequestDto;
import colorful.starbucks.auth.dto.response.TermsResponseDto;
import colorful.starbucks.auth.infrastructure.TermsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TermsServiceImpl implements TermsService {

    private final TermsRepository termsRepository;

    @Transactional
    @Override
    public void createTerms(TermsCreateRequestDto termsCreateRequestDto) {
        Terms terms = Terms.builder()
                        .termsTitle(termsCreateRequestDto.getTermsTitle())
                        .termsContent(termsCreateRequestDto.getTermsContent())
                        .required(termsCreateRequestDto.isRequired())
                        .build();

        termsRepository.save(terms);
    }

    @Override
    public List<TermsResponseDto> getTerms() {
        return termsRepository.findAll().stream()
                .map(terms -> new TermsResponseDto(
                        terms.getTermsTitle(),
                        terms.getTermsContent(),
                        terms.isRequired()
                ) )
                .toList();
    }
}


