package colorful.starbucks.member.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class TermsResponseDto {

    private String termsTitle;

    private String termsContent;

    private Boolean isRequired;

    private String termsCategory;

    public TermsResponseDto(String termsTitle,
                            String termsContent,
                            boolean isRequired,
                            String termsCategory) {
        this.termsTitle = termsTitle;
        this.termsContent = termsContent;
        this.isRequired = isRequired;
        this.termsCategory = termsCategory;
    }
}
