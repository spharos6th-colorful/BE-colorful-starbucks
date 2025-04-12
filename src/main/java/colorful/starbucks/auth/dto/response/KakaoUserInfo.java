package colorful.starbucks.auth.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoUserInfo {

    private String id;
    private String email;

    @Builder
    public KakaoUserInfo(String id,
                         String email) {
        this.id = id;
        this.email = email;
    }
}
