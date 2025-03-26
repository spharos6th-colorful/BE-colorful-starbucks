package colorful.starbucks.auth.dto.response;

import colorful.starbucks.auth.domain.Terms;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TermsAgreementResponseDto {

    private String termsTitle;

    private String termsContent;

    private boolean isRequired;


    public static TermsAgreementResponseDto from(Terms terms) {
        return new TermsAgreementResponseDto(
                terms.getTermsTitle(),
                terms.getTermsContent(),
                terms.isRequired()
        );
    }
}
