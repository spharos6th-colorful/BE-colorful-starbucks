package colorful.starbucks.auth.dto.request;

import colorful.starbucks.auth.vo.request.MemberPasswordResetRequestVo;
import colorful.starbucks.common.util.TempPasswordGenerator;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@NoArgsConstructor
public class MemberPasswordResetRequestDto {

    private String memberName;
    private String email;
    private String phoneNumber;
    private String tempPassword;
    private String encodedPassword;

    @Builder
    private MemberPasswordResetRequestDto(String memberName,
                                          String email,
                                          String phoneNumber) {
        this.memberName = memberName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public static MemberPasswordResetRequestDto from(MemberPasswordResetRequestVo memberPasswordResetRequestVo) {
        return MemberPasswordResetRequestDto.builder()
                .memberName(memberPasswordResetRequestVo.getMemberName())
                .email(memberPasswordResetRequestVo.getEmail())
                .phoneNumber(memberPasswordResetRequestVo.getPhoneNumber())
                .build();
    }


    public void generateTempPassword(PasswordEncoder passwordEncoder) {
        this.tempPassword = TempPasswordGenerator.generate(8);
        this.encodedPassword = passwordEncoder.encode(tempPassword);
    }


    public String getEncodedPassword() {
        return this.encodedPassword;
    }

    public String getTempPassword() {
        return this.tempPassword;
    }
}





