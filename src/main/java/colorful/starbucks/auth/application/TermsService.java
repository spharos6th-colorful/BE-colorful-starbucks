package colorful.starbucks.auth.application;

import colorful.starbucks.auth.vo.request.TermsAgreementRequestVo;

import java.util.List;

public interface TermsService {
     void saveTermsAgreement(List<TermsAgreementRequestVo> agreementVos, String memberUuid);


}
