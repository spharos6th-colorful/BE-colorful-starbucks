package colorful.starbucks.auth.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AccessTokenResponseDto {

    private String accessToken;

    @Builder
    public AccessTokenResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }

    public static AccessTokenResponseDto from(String accessToken) {
        return AccessTokenResponseDto.builder()
                .accessToken(accessToken)
                .build();
    }
}

