package colorful.starbucks.product.infrastructure;

import colorful.starbucks.product.domain.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {
}
