package colorful.starbucks.batch;

import colorful.starbucks.member.domain.MemberLevel;

public class MemberLevelPolicy {
    public static MemberLevel calculateLevel(Long totalAmount) {
        if (totalAmount >= 1000000) {
            return MemberLevel.BLACK;
        } else if (totalAmount >= 500000) {
            return MemberLevel.GOLD;
        } else if (totalAmount >= 100000) {
            return MemberLevel.GREEN;
        } else {
            return MemberLevel.WHITE;
        }
    }
}
