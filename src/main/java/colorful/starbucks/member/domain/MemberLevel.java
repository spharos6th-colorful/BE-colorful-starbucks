package colorful.starbucks.member.domain;

import lombok.Getter;

@Getter
public enum MemberLevel {
    WHITE("스타터"),
    GREEN("패밀리"),
    GOLD("프리미엄"),
    BLACK("엘리트");

    private final String description;

    MemberLevel(String description) {
        this.description = description;
    }



}
