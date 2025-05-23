package colorful.starbucks.product.infrastructure;

import colorful.starbucks.product.domain.BestProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BestProductRepository extends JpaRepository<BestProduct, Long> {
    List<BestProduct> findAllByCategoryIdOrderByRankingAsc(Long categoryId);
}
