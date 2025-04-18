package colorful.starbucks.batch.memberlevel;

import colorful.starbucks.batch.dto.MemberLevelUpdateDto;
import colorful.starbucks.member.domain.MemberLevel;
import colorful.starbucks.member.infrastructure.MemberRepository;
import colorful.starbucks.summary.domain.MemberOrderSummary;
import colorful.starbucks.summary.infrastructure.MemberOrderSummaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class MemberLevelWriter implements ItemWriter<MemberLevelUpdateDto> {

    private final MemberRepository memberRepository;
    private final MemberOrderSummaryRepository memberOrderSummaryRepository;

    @Override
    public void write(Chunk<? extends MemberLevelUpdateDto> chunk) throws Exception {
        chunk.getItems().forEach(update -> {
            MemberLevel newLevel = MemberLevelPolicy.calculateLevel(update.getTotalAmount());
            memberRepository.updateMemberLevel(update.getMemberUuid(), newLevel);

            List<MemberOrderSummary> summaries = memberOrderSummaryRepository.findByMemberUuid(update.getMemberUuid());
            String memberName = memberRepository.findByMemberUuid(update.getMemberUuid()).getMemberName();

            LocalDateTime now = LocalDateTime.now();

            if (!summaries.isEmpty()) {

                MemberOrderSummary existing = summaries.get(0);

                MemberOrderSummary updated = MemberOrderSummary.builder()
                        .id(existing.getId())
                        .memberUuid(update.getMemberUuid())
                        .memberName(memberName)
                        .totalPaidAmount(update.getTotalAmount())
                        .totalOrderCount(existing.getTotalOrderCount() + 1)
                        .lastOrderDate(now)
                        .lastUpdateDate(now)
                        .createdAt(existing.getCreatedAt())
                        .build();

                memberOrderSummaryRepository.save(updated);
            } else {
                MemberOrderSummary created = MemberOrderSummary.builder()
                        .memberUuid(update.getMemberUuid())
                        .memberName(memberName)
                        .totalPaidAmount(update.getTotalAmount())
                        .totalOrderCount(1)
                        .lastOrderDate(now)
                        .lastUpdateDate(now)
                        .createdAt(now)
                        .build();

                memberOrderSummaryRepository.save(created);
            }
        });
    }
}
