package colorful.starbucks.summary.application;

import colorful.starbucks.member.domain.Member;
import colorful.starbucks.order.domain.Order;
import colorful.starbucks.summary.domain.MemberOrderSummary;
import colorful.starbucks.summary.infrastructure.MemberOrderSummaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberOrderSummaryServiceImpl implements MemberOrderSummaryService {

    private final MemberOrderSummaryRepository memberOrderSummaryRepository;

    @Transactional
    @Override
    public void createNewSummary(Order order, Member member) {
        MemberOrderSummary summary = MemberOrderSummary.builder()
                .memberUuid(order.getMemberUuid())
                .memberName(member.getMemberName())
                .totalPaidAmount(order.getTotalAmount())
                .totalOrderCount(1)
                .lastOrderDate(order.getCreatedAt())
                .build();

        memberOrderSummaryRepository.save(summary);
    }
    }


