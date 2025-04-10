package colorful.starbucks.member.dto.response;

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
