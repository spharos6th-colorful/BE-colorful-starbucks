package colorful.starbucks.product.infrastructure;

import colorful.starbucks.product.domain.BestProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BestProductRepository extends JpaRepository<BestProduct, Long> {
}
