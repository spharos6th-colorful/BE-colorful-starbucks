package colorful.starbucks.summary.application;

import colorful.starbucks.member.domain.Member;
import colorful.starbucks.order.domain.Order;

public interface MemberOrderSummaryService {

    void createNewSummary(Order order, Member member);
}
