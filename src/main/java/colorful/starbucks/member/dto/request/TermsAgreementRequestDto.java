package colorful.starbucks.member.dto.request;

import colorful.starbucks.member.vo.request.TermsAgreementRequestVo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class TermsAgreementRequestDto {
    private String memberUuid;

    private List<TermsAgreementRequestVo> agreements;
}

