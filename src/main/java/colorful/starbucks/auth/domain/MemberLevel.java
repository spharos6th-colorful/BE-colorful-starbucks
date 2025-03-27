package colorful.starbucks.auth.domain;

import lombok.Getter;

@Getter
public enum MemberLevel {
    white("스타터"),
    green("패밀리"),
    gold("프리미엄"),
    black("엘리트");

    private final String description;

    MemberLevel(String description) {
        this.description = description;
    }



}
