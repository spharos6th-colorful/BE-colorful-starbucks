package colorful.starbucks.auth.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TermsCreateRequestDto {

    private String termsTitle;

    private String termsContent;

    private boolean isRequired;
}
