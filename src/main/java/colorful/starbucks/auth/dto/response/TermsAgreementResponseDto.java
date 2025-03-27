package colorful.starbucks.auth.dto.response;

import lombok.Getter;

@Getter
public class TermsAgreementResponseDto {

    private String termsTitle;

    private String termsContent;

    private boolean isRequired;


    private TermsAgreementResponseDto(String termsTitle,
                                      String termsContent,
                                      boolean isRequired) {
        this.termsTitle = termsTitle;
        this.termsContent = termsContent;
        this.isRequired = isRequired;
    }

}
