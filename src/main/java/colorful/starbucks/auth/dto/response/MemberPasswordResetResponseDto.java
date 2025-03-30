package colorful.starbucks.auth.dto.response;

import colorful.starbucks.auth.vo.response.MemberPasswordResetResponseVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberPasswordResetResponseDto {

    private String password;

 @Builder
    public MemberPasswordResetResponseDto(String password) {
        this.password = password;
    }

    public static MemberPasswordResetResponseDto from(String password) {
     return MemberPasswordResetResponseDto.builder()
             .password(password)
             .build();
    }

    public MemberPasswordResetResponseVo toVo() {
     return MemberPasswordResetResponseVo.builder()
             .password(password)
             .build();
    }

}
