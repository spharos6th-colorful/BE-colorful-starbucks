package colorful.starbucks.batch.newproduct;

import colorful.starbucks.product.domain.NewProduct;
import colorful.starbucks.product.infrastructure.NewProductRepository;
import colorful.starbucks.product.infrastructure.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Component
public class NewProductSchedulerService {

    private final ProductRepository productRepository;
    private final NewProductRepository newProductRepository;

    @Scheduled(cron = "0 0 5 * * *")
    @Transactional
    public void refreshNewProduct() {

        newProductRepository.deleteAllInBatch();

        LocalDateTime currentTime = LocalDateTime.now();
        List<NewProduct> newProducts = productRepository.findNewProducts(currentTime.minusDays(14), currentTime)
                .stream()
                .map(product -> NewProduct.builder()
                        .productCode(product.getProductCode())
                        .createdAt(product.getCreatedAt())
                        .build())
                .toList();

        newProductRepository.saveAll(newProducts);
    }
}
