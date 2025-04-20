package colorful.starbucks.member.dto.response;

import colorful.starbucks.member.domain.TermsCategory;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class TermsResponseDto {

    private String termsTitle;

    private String termsContent;

    @JsonProperty("isRequired")
    private boolean required;

    private String termsCategory;

    public TermsResponseDto(String termsTitle,
                            String termsContent,
                            boolean required,
                            String termsCategory) {
        this.termsTitle = termsTitle;
        this.termsContent = termsContent;
        this.required = required;
        this.termsCategory = termsCategory;
    }
}
