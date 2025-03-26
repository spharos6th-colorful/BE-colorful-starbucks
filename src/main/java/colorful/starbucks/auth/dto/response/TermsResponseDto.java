package colorful.starbucks.auth.dto.response;

import colorful.starbucks.auth.domain.Terms;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TermsResponseDto {

    private String termsTitle;

    private String termsContent;

    private Boolean isRequired;


    public static TermsResponseDto from(Terms terms) {
        return new TermsResponseDto(
                terms.getTermsTitle(),
                terms.getTermsContent(),
                terms.isRequired()
        );
    }
}
