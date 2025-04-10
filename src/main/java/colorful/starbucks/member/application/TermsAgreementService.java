package colorful.starbucks.member.application;

import colorful.starbucks.member.vo.request.TermsAgreementRequestVo;

import java.util.List;

public interface TermsAgreementService {
    void saveTermsAgreement(List<TermsAgreementRequestVo> agreementVos, String memberUuid);
}
