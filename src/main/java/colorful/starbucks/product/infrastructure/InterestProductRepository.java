package colorful.starbucks.product.infrastructure;

import colorful.starbucks.product.domain.InterestProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestProductRepository extends JpaRepository<InterestProduct, Long> {
}
