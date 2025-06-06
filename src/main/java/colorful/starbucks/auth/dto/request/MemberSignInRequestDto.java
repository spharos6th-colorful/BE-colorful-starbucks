package colorful.starbucks.auth.dto.request;

import colorful.starbucks.auth.vo.request.MemberSignInRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberSignInRequestDto {

    private String email;
    private String password;

    @Builder
    private MemberSignInRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static MemberSignInRequestDto from(MemberSignInRequestVo memberSignInRequestVo) {
        return MemberSignInRequestDto.builder()
                .email(memberSignInRequestVo.getEmail())
                .password(memberSignInRequestVo.getPassword())
                .build();
    }


}
