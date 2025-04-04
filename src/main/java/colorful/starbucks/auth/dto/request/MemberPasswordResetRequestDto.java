package colorful.starbucks.auth.dto.request;

import colorful.starbucks.member.domain.Member;
import colorful.starbucks.auth.infrastructure.AuthRepository;
import colorful.starbucks.auth.vo.request.MemberPasswordResetRequestVo;
import colorful.starbucks.common.util.TempPasswordGenerator;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Getter
public class MemberPasswordResetRequestDto {

    private final String memberName;
    private final String email;
    private final String phoneNumber;

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


    public Optional<Member> findMatchingMember(AuthRepository repository) {
        return repository.findByEmailAndMemberNameAndPhoneNumber(
                email.trim(),
                memberName.trim(),
                phoneNumber.trim()
        );
    }

    public String getEncodedPassword() {
        return this.encodedPassword;
    }

    public String getTempPassword() {
        return this.tempPassword;
    }
}





