package colorful.starbucks.member.dto.response;

import colorful.starbucks.member.domain.Gender;
import colorful.starbucks.member.domain.Member;
import colorful.starbucks.member.domain.MemberLevel;
import colorful.starbucks.member.vo.response.MemberMyPageResponseVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberMyPageResponseDto {

    private String memberName;
    private String email;
    private String phoneNumber;
    private String nickName;
    private MemberLevel memberLevel;
    private String memberBirth;
    private Gender gender;

    @Builder
    private MemberMyPageResponseDto(String memberName,
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

    public MemberMyPageResponseVo toVo(){
        return MemberMyPageResponseVo.builder()
                .memberName(memberName)
                .email(email)
                .phoneNumber(phoneNumber)
                .nickName(nickName)
                .memberLevel(memberLevel)
                .memberBirth(memberBirth)
                .gender(gender)
                .build();
    }

    public static MemberMyPageResponseDto from(Member member){
        return MemberMyPageResponseDto.builder()
                .memberName(member.getMemberName())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .nickName(member.getNickName())
                .memberLevel(member.getMemberLevel())
                .memberBirth(member.getMemberBirth())
                .gender(member.getGender())
                .build();
    }

}
