package colorful.starbucks.auth.vo.request;

import colorful.starbucks.member.domain.Gender;
import colorful.starbucks.member.domain.MemberLevel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class MemberSignUpRequestVo {

    @Size(min = 1, max = 50)
    @NotBlank(message = "이름은 필수입니다.")
    private String memberName;

    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "이메일 형식에 맞게 입력해주세요.")
    private String email;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min = 8, max = 20)
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[~!@#$%^& ]).*$",
            message = "비밀번호는 영문, 숫자, 특수문자를 포함하여 8자 이상으로 입력해주세요.")
    private String password;

    @NotBlank(message = "전화번호는 필수입니다.")
    private String phoneNumber;

    @NotBlank(message = "닉네임은 필수입니다.")
    @Size(min = 1, max = 20, message = "닉네임은 1자 이상 20자 이하로 입력해주세요.")
    private String nickName;

    @NotBlank(message = "생년월일은 필수입니다.")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$" , message = "생년월일은 yyyy-MM-dd 형식으로 입력해주세요.")
    private String memberBirth;

    private MemberLevel memberLevel;

    @NotBlank(message = "성별은 필수입니다.")
    private Gender gender;
}
