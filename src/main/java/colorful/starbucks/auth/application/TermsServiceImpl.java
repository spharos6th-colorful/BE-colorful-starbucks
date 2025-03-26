package colorful.starbucks.auth.application;

import colorful.starbucks.auth.domain.Terms;
import colorful.starbucks.auth.domain.TermsAgreement;
import colorful.starbucks.auth.dto.request.TermsCreateRequestDto;
import colorful.starbucks.auth.infrastructure.TermsAgreementRepository;
import colorful.starbucks.auth.infrastructure.TermsRepository;
import colorful.starbucks.auth.vo.request.TermsAgreementRequestVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TermsServiceImpl implements TermsService {

    private final TermsAgreementRepository termsAgreementRepository;
    private final TermsRepository termsRepository;

    @Override
    @Transactional
    public void saveTermsAgreement(List<TermsAgreementRequestVo> agreementVos, String memberUuid) {

        List<TermsAgreement> agreements = agreementVos.stream()
                .map(vo -> {
                    Terms terms = termsRepository.findById(vo.getTermsId())
                            .orElseThrow(() -> new IllegalArgumentException("해당 약관이 존재하지 않습니다."));

                    return TermsAgreement.builder()
                            .terms(terms)
                            .isAgreed(vo.isAgreed())
                            .memberUuid(memberUuid)
                            .build();
                })
                .toList();

        termsAgreementRepository.saveAll(agreements);
    }

    @Override
    @Transactional
    public void createTerms(TermsCreateRequestDto termsCreateRequestDto) {
        Terms terms = Terms.builder()
                        .termsTitle(termsCreateRequestDto.getTermsTitle())
                        .termsContent(termsCreateRequestDto.getTermsContent())
                        .required(termsCreateRequestDto.isRequired())
                        .build();

        termsRepository.save(terms);
    }
}


