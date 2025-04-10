package colorful.starbucks.auth.dto.response;

import colorful.starbucks.auth.vo.response.MemberPasswordResetResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberPasswordResetResponseDto {

    private String message;

 @Builder
    private MemberPasswordResetResponseDto(String message) {
        this.message = message;
    }

    public static MemberPasswordResetResponseDto from(String message) {
     return MemberPasswordResetResponseDto.builder()
             .message(message)
             .build();
    }

    public MemberPasswordResetResponseVo toVo() {
     return MemberPasswordResetResponseVo.builder()
             .message(message)
             .build();
    }
}
