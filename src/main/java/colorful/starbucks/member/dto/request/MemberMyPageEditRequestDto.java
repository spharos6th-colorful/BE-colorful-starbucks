package colorful.starbucks.member.dto.request;

import colorful.starbucks.member.domain.Member;
import colorful.starbucks.member.vo.request.MemberMyPageEditRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberMyPageEditRequestDto {

    private String phoneNumber;
    private String nickName;
    private String memberUuid;

    @Builder
    private MemberMyPageEditRequestDto(String phoneNumber,
                                       String nickName,
                                       String memberUuid) {
        this.phoneNumber = phoneNumber;
        this.nickName = nickName;
        this.memberUuid = memberUuid;
    }

    public static MemberMyPageEditRequestDto of(MemberMyPageEditRequestVo memberMyPageEditRequestVo,
                                                String memberUuid) {
        return MemberMyPageEditRequestDto.builder()
                .phoneNumber(memberMyPageEditRequestVo.getPhoneNumber())
                .nickName(memberMyPageEditRequestVo.getNickName())
                .memberUuid(memberUuid)
                .build();

    }


}
