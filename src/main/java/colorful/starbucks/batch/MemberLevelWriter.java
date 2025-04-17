package colorful.starbucks.batch;

import colorful.starbucks.batch.dto.MemberLevelUpdateDto;
import colorful.starbucks.member.domain.MemberLevel;
import colorful.starbucks.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

@RequiredArgsConstructor
public class MemberLevelWriter implements ItemWriter<MemberLevelUpdateDto> {

    private final MemberRepository memberRepository;

    @Override
    public void write(Chunk<? extends MemberLevelUpdateDto> chunk) throws Exception {
        chunk.getItems().forEach(update -> {
            MemberLevel newLevel = MemberLevelPolicy.calculateLevel(update.getTotalAmount());
            memberRepository.updateMemberLevel(update.getMemberUuid(), newLevel);
        });
    }
}



