package colorful.starbucks.auth.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TermsResponseDto {

    private String termsTitle;

    private String termsContent;

    @JsonProperty("isRequired")
    private boolean required;
}
