package colorful.starbucks.product.infrastructure;

import colorful.starbucks.product.domain.NewProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewProductRepository extends JpaRepository<NewProduct, Long> {
}
