package colorful.starbucks.member.dto.response;

import lombok.Getter;

@Getter
public class TermsAgreementResponseDto {

    private String termsTitle;

    private String termsContent;

    private Boolean isRequired;


    private TermsAgreementResponseDto(String termsTitle,
                                      String termsContent,
                                      Boolean isRequired) {
        this.termsTitle = termsTitle;
        this.termsContent = termsContent;
        this.isRequired = isRequired;
    }

}
