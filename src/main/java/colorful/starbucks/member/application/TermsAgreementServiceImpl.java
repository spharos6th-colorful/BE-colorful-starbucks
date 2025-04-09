package colorful.starbucks.member.application;

import colorful.starbucks.member.domain.Terms;
import colorful.starbucks.member.domain.TermsAgreement;
import colorful.starbucks.member.infrastructure.TermsAgreementRepository;
import colorful.starbucks.member.infrastructure.TermsRepository;
import colorful.starbucks.member.vo.request.TermsAgreementRequestVo;
import colorful.starbucks.common.exception.BaseException;
import colorful.starbucks.common.response.ResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TermsAgreementServiceImpl implements TermsAgreementService {

    private final TermsAgreementRepository termsAgreementRepository;
    private final TermsRepository termsRepository;

    @Transactional
    @Override
    public void saveTermsAgreement(List<TermsAgreementRequestVo> agreementVos, String memberUuid) {

        List<TermsAgreement> agreements = agreementVos.stream()
                .map(vo -> {
                    Terms terms = termsRepository.findById(vo.getTermsId())
                            .orElseThrow(() -> new BaseException(ResponseStatus.NO_EXIST_TERMS));
                    return TermsAgreement.builder()
                            .termsId(terms.getId())
                            .isAgreed(vo.isAgreed())
                            .memberUuid(memberUuid)
                            .build();
                })
                .toList();

        termsAgreementRepository.saveAll(agreements);
    }
}
