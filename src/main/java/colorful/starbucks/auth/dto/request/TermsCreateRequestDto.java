package colorful.starbucks.auth.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TermsCreateRequestDto {

    private String termsTitle;

    private String termsContent;

    @JsonProperty("isRequired")
    private boolean required;
}
