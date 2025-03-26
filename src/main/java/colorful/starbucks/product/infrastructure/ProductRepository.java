package colorful.starbucks.product.infrastructure;

import colorful.starbucks.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
