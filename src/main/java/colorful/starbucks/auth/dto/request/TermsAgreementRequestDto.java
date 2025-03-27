package colorful.starbucks.auth.dto.request;

import colorful.starbucks.auth.vo.request.TermsAgreementRequestVo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class TermsAgreementRequestDto {
    private String memberUuid;

    private List<TermsAgreementRequestVo> agreements;

    private List<TermsAgreementRequestVo> termsAgreements;
}

