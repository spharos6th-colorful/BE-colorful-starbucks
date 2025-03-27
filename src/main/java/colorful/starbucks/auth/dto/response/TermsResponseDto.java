package colorful.starbucks.auth.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class TermsResponseDto {

    private String termsTitle;

    private String termsContent;

    @JsonProperty("isRequired")
    private boolean required;

    public TermsResponseDto(String termsTitle,
                            String termsContent,
                            boolean required) {
        this.termsTitle = termsTitle;
        this.termsContent = termsContent;
        this.required = required;
    }
}
