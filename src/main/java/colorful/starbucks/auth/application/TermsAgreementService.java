package colorful.starbucks.auth.application;

import colorful.starbucks.auth.vo.request.TermsAgreementRequestVo;

import java.util.List;

public interface TermsAgreementService {
    void saveTermsAgreement(List<TermsAgreementRequestVo> agreementVos, String memberUuid);
}
