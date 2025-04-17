package colorful.starbucks.batch.dto;

import colorful.starbucks.member.domain.MemberLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberLevelTargetDto {

    private String memberUuid;
    private MemberLevel memberLevel;
    private Integer totalAmount;


    public MemberLevelTargetDto(String memberUuid,
                                MemberLevel memberLevel,
                                Integer totalAmount
    ) {
        this.memberUuid = memberUuid;
        this.memberLevel = memberLevel;
        this.totalAmount = totalAmount;

    }


}
