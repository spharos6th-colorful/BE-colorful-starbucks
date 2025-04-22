package colorful.starbucks.batch.bestproduct;

import colorful.starbucks.batch.dto.ProductsAndQuantities;
import colorful.starbucks.batch.dto.ProductInfoForBestProductBatch;
import colorful.starbucks.product.domain.BestProduct;
import colorful.starbucks.product.infrastructure.BestProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RequiredArgsConstructor
public class BestProductWriter implements ItemWriter<ProductInfoForBestProductBatch>, StepExecutionListener {

    private final BestProductRepository bestProductRepository;

    private final Map<Long, ProductsAndQuantities> CategoryProductsMap = new HashMap<>();
    private final List<BestProduct> bestProducts = new ArrayList<>();

    @Override
    public void write(Chunk<? extends ProductInfoForBestProductBatch> chunk) {
        for (ProductInfoForBestProductBatch item : chunk) {
            Long productCode = item.getProductCode();
            int quantity = item.getQuantity();

            CategoryProductsMap
                    .computeIfAbsent(item.getCategoryId(), categoryId -> new ProductsAndQuantities(item.getCategoryName()))
                    .addProductQuantity(productCode, quantity);
        }
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        CategoryProductsMap.clear();
        bestProducts.clear();
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {

        CategoryProductsMap.forEach((categoryId, value) -> {
            Map<Long, Integer> productCountMap = value.getProductCodeAndQuantityMap();

            List<Map.Entry<Long, Integer>> productAndQuantityList = productCountMap.entrySet().stream()
                    .sorted(Map.Entry.<Long, Integer>comparingByValue().reversed())
                    .limit(30)
                    .toList();

            AtomicInteger rankCounter = new AtomicInteger(1);
            productAndQuantityList.forEach(productAndQuantityEntry -> bestProducts.add(
                    BestProduct.builder()
                            .productCode(productAndQuantityEntry.getKey())
                            .categoryId(categoryId)
                            .categoryName(value.getCategoryName())
                            .totalQuantity(productAndQuantityEntry.getValue())
                            .ranking(rankCounter.getAndIncrement())
                            .build()
            ));
        });

        bestProductRepository.saveAll(bestProducts);
        return ExitStatus.COMPLETED;
    }
}
