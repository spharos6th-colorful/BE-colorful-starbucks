package colorful.starbucks.batch;

import colorful.starbucks.batch.dto.MemberLevelTargetDto;
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

    private final MemberOrderSummaryRepository memberOrderSummaryRepository;

    @Bean
    public Job memberLevelBatchJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("memberLevelBatchJob", jobRepository)
                .start(chunkStep(jobRepository, transactionManager))
                .build();
    }

    @Bean
    public Step chunkStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("memberLevelBatchStep", jobRepository)
                .<MemberLevelTargetDto, MemberLevelTargetDto>chunk(5000, transactionManager)
                .reader(memberLevelReader(null))
                .processor(memberLevelProcessor())
                .writer(memberLevelWriter())
                .listener(memberLevelWriter())
                .build();
    }

    @Bean
    @StepScope
    public JpaPagingItemReader<MemberLevelTargetDto> memberLevelReader(EntityManagerFactory emf) {

        LocalDateTime currentBatchTime = LocalDateTime.now();
        return new JpaPagingItemReaderBuilder<MemberLevelTargetDto>()
                .name("memberLevelReader")
                .entityManagerFactory(emf)
                .pageSize(5000)
                .queryString(
                        "SELECT new colorful.starbucks.batch.dto.MemberLevelTargetDto(" +
                         "m.memberUuid, m.memberLevel, SUM(o.total_amount) o.totalAmount " +
                        "FROM Order o " +
                        "JOIN member m ON o.memberUuid = m.memberUuid " +
                        "WHERE o.orderStatus = 'PAID' " +
                                "AND o.createdAt >= DATESUB(NOW(), INTERVAL 3 MONTH) " +
                        "GROUP BY m.memberUuid, m.memberLevel"
                )
                .parameterValues( Map.of("currentBatchTime", currentBatchTime))
                .build();
    }

    @Bean
    @StepScope
    public ItemProcessor<MemberLevelTargetDto, MemberLevelTargetDto> memberLevelProcessor() {
        return memberLevelTargetDto -> memberLevelTargetDto;
    }

    @Bean
    public ItemWriter<MemberLevelTargetDto> memberLevelWriter() {
        return new MemberLevelWriter(memberOrderSummaryRepository);
    }

}
