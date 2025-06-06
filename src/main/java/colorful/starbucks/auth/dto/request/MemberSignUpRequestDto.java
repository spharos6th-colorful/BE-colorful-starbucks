package colorful.starbucks.auth.dto.request;

import colorful.starbucks.member.domain.Gender;
import colorful.starbucks.member.domain.Member;
import colorful.starbucks.member.domain.MemberLevel;
import colorful.starbucks.auth.vo.request.MemberSignUpRequestVo;
import colorful.starbucks.common.util.UuidGenerator;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@NoArgsConstructor
public class MemberSignUpRequestDto {

    private String memberName;
    private String email;
    private String password;
    private String phoneNumber;
    private String nickName;
    private String memberBirth;
    private MemberLevel memberLevel = MemberLevel.WHITE;
    private Gender gender;


    @Builder
    public MemberSignUpRequestDto(String memberName,
                                  String email,
                                  String password,
                                  String phoneNumber,
                                  String nickName,
                                  String memberBirth,
                                  MemberLevel memberLevel,
                                  Gender gender) {
        this.memberName = memberName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.nickName = nickName;
        this.memberBirth = memberBirth;
        this.memberLevel = memberLevel;
        this.gender = gender;
    }




    public Member toEntity(String memberUuid, String encodedPassword) {
        return Member.builder()
                .memberUuid(memberUuid)
                .memberName(memberName)
                .email(email)
                .password(encodedPassword)
                .phoneNumber(phoneNumber)
                .nickName(nickName)
                .memberBirth(memberBirth)
                .memberLevel(memberLevel)
                .gender(gender)
                .build();

    }

    public static MemberSignUpRequestDto from(MemberSignUpRequestVo memberSignUpRequestVo) {
        return  MemberSignUpRequestDto.builder()
                .memberName(memberSignUpRequestVo.getMemberName())
                .email(memberSignUpRequestVo.getEmail())
                .password(memberSignUpRequestVo.getPassword())
                .phoneNumber(memberSignUpRequestVo.getPhoneNumber())
                .nickName(memberSignUpRequestVo.getNickName())
                .memberBirth(memberSignUpRequestVo.getMemberBirth())
                .memberLevel(memberSignUpRequestVo.getMemberLevel() != null ? memberSignUpRequestVo.getMemberLevel() :
                        MemberLevel.WHITE)
                .gender(memberSignUpRequestVo.getGender())
                .build();
    }

    public Member toEntityWithEncodePassword(PasswordEncoder passwordEncoder){
        String encodedPassword = passwordEncoder.encode(password);
        String memberUuid = UuidGenerator.generateUuid();
        return this.toEntity(memberUuid, encodedPassword);
    }




}
