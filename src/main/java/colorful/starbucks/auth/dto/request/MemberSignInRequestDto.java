package colorful.starbucks.auth.dto.request;

import colorful.starbucks.auth.dto.response.MemberSignInResponseDto;
import colorful.starbucks.auth.vo.request.MemberSignInRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberSignInRequestDto {

    private String email;

    private String password;

    @Builder
    private MemberSignInRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    private static MemberSignInRequestDto from(MemberSignInRequestVo memberSignInRequestVo) {
        return MemberSignInRequestDto.builder()
                .email(memberSignInRequestVo.getEmail())
                .password(memberSignInRequestVo.getPassword())
                .build();
    }

}
