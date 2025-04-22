package colorful.starbucks.batch.bestproduct;

import colorful.starbucks.batch.dto.ProductInfoForBestProductBatch;
import colorful.starbucks.product.infrastructure.BestProductRepository;
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
public class BestProductBatchJob {

    private final BestProductRepository bestProductRepository;

    @Bean
    public Job bestProductJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("bestProductJob", jobRepository)
                .start(chunkStep(jobRepository, transactionManager))
                .build();
    }

    @Bean
    public Step chunkStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("chunkStep", jobRepository)
                .<ProductInfoForBestProductBatch, ProductInfoForBestProductBatch>chunk(100000, transactionManager)
                .reader(orderDetailReader(null))
                .processor(orderDetailProcessor())
                .writer(productAggregatorWriter())
                .listener(productAggregatorWriter())
                .build();
    }

    @Bean
    @StepScope
    public JpaPagingItemReader<ProductInfoForBestProductBatch> orderDetailReader(EntityManagerFactory emf) {

        LocalDateTime currentBatchTime = LocalDateTime.now();
        return new JpaPagingItemReaderBuilder<ProductInfoForBestProductBatch>()
                .name("orderDetailReader")
                .entityManagerFactory(emf)
                .pageSize(100000)
                .queryString(
                        "SELECT new colorful.starbucks.batch.dto.ProductInfoForBestProductBatch(" +
                        "od.productCode, od.quantity, pf.topCategoryId, pf.topCategoryName) " +
                        "FROM OrderDetail od " +
                        "INNER JOIN ProductFilter pf ON od.productCode = pf.productCode " +
                        "WHERE od.createdAt >= :startDate AND od.createdAt <= :endDate " +
                        "ORDER BY od.id ASC")
                .parameterValues(Map.of(
                        "startDate", currentBatchTime.minusMonths(1),
                        "endDate", currentBatchTime.minusHours(1))
                )
                .build();
    }

    @Bean
    @StepScope
    public ItemProcessor<ProductInfoForBestProductBatch, ProductInfoForBestProductBatch> orderDetailProcessor() {
        return productInfoForBestProductBatch -> productInfoForBestProductBatch;
    }

    @Bean
    public ItemWriter<ProductInfoForBestProductBatch> productAggregatorWriter() {
        return new BestProductWriter(bestProductRepository);
    }
}
