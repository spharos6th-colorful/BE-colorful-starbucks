package colorful.starbucks.batch.dto;

import colorful.starbucks.member.domain.MemberLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberLevelTargetDto {

    private String memberUuid;
    private MemberLevel memberLevel;
    private Long totalAmount;


    public MemberLevelTargetDto(String memberUuid,
                                MemberLevel memberLevel,
                                Long totalAmount
    ) {
        this.memberUuid = memberUuid;
        this.memberLevel = memberLevel;
        this.totalAmount = totalAmount;

    }


}
