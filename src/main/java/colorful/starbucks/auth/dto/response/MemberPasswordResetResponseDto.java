package colorful.starbucks.auth.dto.response;

import colorful.starbucks.auth.vo.response.MemberPasswordResetResponseVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberPasswordResetResponseDto {

    private String message;

 @Builder

    public MemberPasswordResetResponseDto(String message) {
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
