package colorful.starbucks.member.dto.request;

import colorful.starbucks.member.domain.TermsCategory;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class TermsCreateRequestDto {

    private String termsTitle;

    private String termsContent;

    private Boolean isRequired;

    private TermsCategory termsCategory;
}
