package colorful.starbucks.batch.memberlevel;

import colorful.starbucks.batch.dto.MemberLevelTargetDto;
import colorful.starbucks.batch.dto.MemberLevelUpdateDto;
import colorful.starbucks.member.infrastructure.MemberRepository;
import colorful.starbucks.summary.infrastructure.MemberOrderSummaryRepository;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.time.LocalDateTime;
import java.util.Map;

@RequiredArgsConstructor
@Configuration
public class MemberLevelBatchJob {

    private final MemberRepository memberRepository;
    private final MemberOrderSummaryRepository memberOrderSummaryRepository;

    @Override
    protected void finalize() throws Throwable {
        finalize();
    }

    @Bean
    public Job memberLevelJob(JobRepository jobRepository,
                                   PlatformTransactionManager transactionManager,
                                   EntityManagerFactory entityManagerFactory) {

        Step memberLevelStep = new StepBuilder("memberLevelReader", jobRepository)
                .<MemberLevelTargetDto, MemberLevelUpdateDto>chunk(5000, transactionManager)
                .reader(memberLevelReader(entityManagerFactory))
                .processor(memberLevelProcessor())
                .writer(memberLevelWriter())
                .build();

        return new JobBuilder("memberLevelJob", jobRepository)
                .start(memberLevelStep)
                .build();
    }

    @Bean
    @StepScope
    public JpaPagingItemReader<MemberLevelTargetDto> memberLevelReader(EntityManagerFactory emf) {
        LocalDateTime threeMonthsAgo = LocalDateTime.now().minusMonths(3);

        return new JpaPagingItemReaderBuilder<MemberLevelTargetDto>()
                .name("memberLevelReader")
                .entityManagerFactory(emf)
                .pageSize(5000)
                .queryString(
                        "SELECT new colorful.starbucks.batch.dto.MemberLevelTargetDto(" +
                                "m.memberUuid, m.memberLevel, SUM(o.totalAmount)) " +
                                "FROM Order o " +
                                "JOIN Member m " +
                                "ON o.memberUuid = m.memberUuid " +
                                "WHERE o.orderStatus = 'PAID' " +
                                "AND o.createdAt >= :threeMonthsAgo " +
                                "GROUP BY m.memberUuid, m.memberLevel"
                )
                .parameterValues(Map.of("threeMonthsAgo", threeMonthsAgo))
                .build();
    }

    @Bean
    @StepScope
    public ItemProcessor<MemberLevelTargetDto, MemberLevelUpdateDto> memberLevelProcessor() {
        return item -> new MemberLevelUpdateDto(
                item.getMemberUuid(),
                item.getMemberLevel(),
                item.getTotalAmount()
        );
    }

    @Bean
    public ItemWriter<MemberLevelUpdateDto> memberLevelWriter() {
        return new MemberLevelWriter(memberRepository,memberOrderSummaryRepository);
    }
}
