package colorful.starbucks.auth.domain;

import lombok.Getter;

@Getter
public enum SignType {
    NORMAL("일반회원"),
    KAKAO("카카오 로그인 회원"),
    GOOGLE("구글 로그인 회원");

    private final String description;

    SignType(String description) {
        this.description = description;
    }
}
