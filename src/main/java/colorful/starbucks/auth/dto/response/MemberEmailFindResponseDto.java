package colorful.starbucks.auth.dto.response;

import colorful.starbucks.auth.vo.response.MemberEmailFindResponseVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberEmailFindResponseDto {

    private String email;



    @Builder
    public MemberEmailFindResponseDto(String email) {
        this.email = email;
    }

    public static MemberEmailFindResponseDto from(String email) {
        return MemberEmailFindResponseDto.builder()
                .email(email)
                .build();
    }

    public MemberEmailFindResponseVo toVo(){
        return MemberEmailFindResponseVo.builder()
                .email(email)
                .build();
    }

}
