package colorful.starbucks.auth.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class KakaoUserInfo {

    private final String id;

    private final String email;

    @Builder
    public KakaoUserInfo(String id,
                         String email) {
        this.id = id;
        this.email = email;
    }
}
