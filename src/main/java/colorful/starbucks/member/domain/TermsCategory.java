package colorful.starbucks.member.domain;

import colorful.starbucks.common.exception.BaseException;
import colorful.starbucks.common.response.ResponseStatus;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum TermsCategory {
    MEMBER("회원약관"),
    DELIVERY("배송약관"),
    PRIVACY("개인정보처리방침"),
    MARKETING("마케팅정보 수신 동의");

    private final String description;
    TermsCategory(String description) {
        this.description = description;
    }
    public static TermsCategory getCategory(String description) {
        return Arrays.stream(values())
                .filter(termsCategory -> termsCategory.getDescription().equals(description))
                .findFirst()
                .orElseThrow(() -> new BaseException(ResponseStatus.NO_EXIST_TERMS, "존재하지 않는 약관입니다.: " + description));
    }


}
