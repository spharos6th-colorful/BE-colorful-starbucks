package colorful.starbucks.auth.domain;

import lombok.Getter;

@Getter
public enum SignType {
    KAKAO("카카오 로그인 회원"),
    GOOGLE("구글 로그인 회원"),
    APPLE("애플 로그인 회원"),
    NAVER("네이버 로그인 회원");

    private final String description;

    SignType(String description) {
        this.description = description;
    }
}
