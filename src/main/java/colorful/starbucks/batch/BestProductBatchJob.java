package colorful.starbucks.batch;

import colorful.starbucks.admin.domain.ProductCategoryList;
import colorful.starbucks.admin.infrastructure.ProductReadRepository;
import colorful.starbucks.order.domain.OrderDetail;
import colorful.starbucks.product.domain.BestProduct;
import colorful.starbucks.product.domain.Product;
import colorful.starbucks.product.infrastructure.BestProductRepository;
import colorful.starbucks.product.infrastructure.ProductRepository;
import jakarta.persistence.EntityManagerFactory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.DuplicateJobException;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.batch.item.support.AbstractItemStreamItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RequiredArgsConstructor
@Configuration
public class BestProductBatchJob {

    private final BestProductRepository bestProductRepository;
    private final ProductReadRepository productReadRepository;
    private final ProductRepository productRepository;

    @Bean
    public Job bestProductJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) throws DuplicateJobException {
        return new JobBuilder("bestProductJob",jobRepository)
                .start(chunkStep1(jobRepository,transactionManager))
                .build();
    }

    @Bean
    public Step chunkStep1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("chunkStep", jobRepository)
                .<OrderDetail, ProductCodeAndQuantity>chunk(100, transactionManager)
                .reader(orderDetailReader(null))
                .processor(orderDetailProcessor())
                .writer(productAggregatorWriter())
                .build();
    }

    @Bean
    @StepScope
    public JpaPagingItemReader<OrderDetail> orderDetailReader(EntityManagerFactory emf) {
        return new JpaPagingItemReaderBuilder<OrderDetail>()
                .name("orderDetailReader")
                .entityManagerFactory(emf)
                .pageSize(2)
                .queryString("SELECT od FROM OrderDetail od where od.createdAt >= :startDate and od.createdAt <= :endDate order by od.id asc")
                .parameterValues(Map.of(
                        "startDate", LocalDateTime.now().minusMonths(1),
                        "endDate", LocalDateTime.now())
                )
                .build();
    }

    @Bean
    @StepScope
    public ItemProcessor<OrderDetail, ProductCodeAndQuantity> orderDetailProcessor() {
        return orderDetail -> new ProductCodeAndQuantity(
                orderDetail.getProductCode(),
                orderDetail.getQuantity()
        );
    }

    @Bean
    @StepScope
    public ItemWriter<ProductCodeAndQuantity> productAggregatorWriter() {
        return new ItemWriter<>() {

            private final Map<Long, Integer> productCountMap = new HashMap<>();

            @Override
            public void write(Chunk<? extends ProductCodeAndQuantity> chunk) {
                for (ProductCodeAndQuantity item : chunk) {
                    Long productCode = item.getProductCode();
                    int quantity = item.getQuantity();

                    productCountMap.put(
                            productCode,
                            productCountMap.getOrDefault(productCode, 0) + quantity
                    );
                }

                List<Map.Entry<Long, Integer>> top30 = productCountMap.entrySet().stream()
                        .sorted(Map.Entry.<Long, Integer>comparingByValue().reversed())
                        .limit(30)
                        .toList();

                AtomicInteger rankCounter = new AtomicInteger(1);

                List<BestProduct> bestProducts = top30.stream()
                        .map(entry -> {
                            Product product = productRepository.findByProductCodeAndIsDeletedIsFalse(entry.getKey())
                                    .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));

                            return BestProduct.builder()
                                    .productCode(entry.getKey())
//                                    .categoryId(product.getTopCategoryId())
//                                    .categoryName(product.getTopCategoryName())
                                    .totalQuantity(entry.getValue())
                                    .productRank(rankCounter.getAndIncrement())
                                    .build();
                        })
                        .toList();

                bestProductRepository.saveAll(bestProducts);
            }
        };
    }
}
