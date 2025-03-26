package colorful.starbucks.auth.dto.request;

import colorful.starbucks.auth.domain.Member;
import colorful.starbucks.auth.vo.request.MemberSignUpRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberSignUpRequestDto {

    private String memberName;

    private String email;

    private String password;

    private String phoneNumber;

    private String nickName;

    private String memberBirth;

    public Member toEntity(String memberUuid, String encodedPassword) {
        return Member.builder()
                .memberUuid(memberUuid)
                .memberName(memberName)
                .email(email)
                .password(encodedPassword)
                .phoneNumber(phoneNumber)
                .nickName(nickName)
                .memberBirth(memberBirth)
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
                .build();
    }




}
