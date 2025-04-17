package colorful.starbucks.batch;

import colorful.starbucks.batch.dto.MemberLevelTargetDto;
import colorful.starbucks.batch.dto.MemberLevelUpdateDto;
import colorful.starbucks.member.domain.MemberLevel;
import colorful.starbucks.member.infrastructure.MemberRepository;
import colorful.starbucks.summary.infrastructure.MemberOrderSummaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class MemberLevelWriter implements ItemWriter<MemberLevelTargetDto> {

    private final MemberOrderSummaryRepository memberOrderSummaryRepository;

    }
}
