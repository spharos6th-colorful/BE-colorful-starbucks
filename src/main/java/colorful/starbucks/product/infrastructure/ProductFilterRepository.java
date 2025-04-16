package colorful.starbucks.product.infrastructure;

import colorful.starbucks.product.domain.ProductFilter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductFilterRepository extends JpaRepository<ProductFilter, Long>, ProductFilterRepositoryCustom {
}
