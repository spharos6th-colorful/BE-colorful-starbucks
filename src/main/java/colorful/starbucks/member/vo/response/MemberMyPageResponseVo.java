package colorful.starbucks.member.vo.response;

import colorful.starbucks.member.domain.Gender;
import colorful.starbucks.member.domain.MemberLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberMyPageResponseVo {

    private String memberName;
    private String email;
    private String phoneNumber;
    private String nickName;
    private MemberLevel memberLevel;
    private String memberBirth;
    private Gender gender;

    @Builder
    private MemberMyPageResponseVo(String memberName,
                                   String email,
                                   String phoneNumber,
                                   String nickName,
                                   MemberLevel memberLevel,
                                   String memberBirth,
                                   Gender gender) {
        this.memberName = memberName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.nickName = nickName;
        this.memberLevel = memberLevel;
        this.memberBirth = memberBirth;
        this.gender = gender;
    }
}
