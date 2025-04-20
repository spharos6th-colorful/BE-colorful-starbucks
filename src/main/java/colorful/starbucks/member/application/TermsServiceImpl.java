package colorful.starbucks.member.application;

import colorful.starbucks.member.domain.Terms;
import colorful.starbucks.member.domain.TermsCategory;
import colorful.starbucks.member.dto.request.TermsCreateRequestDto;
import colorful.starbucks.member.dto.response.TermsResponseDto;
import colorful.starbucks.member.infrastructure.TermsRepository;
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
                        .termsCategory(termsCreateRequestDto.getTermsCategory())
                        .build();

        termsRepository.save(terms);
    }

    @Override
    public List<TermsResponseDto> getTerms() {
        return termsRepository.findAll().stream()
                .map(terms -> new TermsResponseDto(
                        terms.getTermsTitle(),
                        terms.getTermsContent(),
                        terms.isRequired(),
                        terms.getTermsCategory().getDescription()
                ) )
                .toList();
    }
    @Override
    public List<TermsResponseDto> getTermsByCategory(TermsCategory termsCategory) {
        return termsRepository.findByTermsCategory(termsCategory).stream()
                .map(terms -> new TermsResponseDto(
                        terms.getTermsTitle(),
                        terms.getTermsContent(),
                        terms.isRequired(),
                        terms.getTermsCategory().getDescription()
                ))
                .toList();
    }
}


