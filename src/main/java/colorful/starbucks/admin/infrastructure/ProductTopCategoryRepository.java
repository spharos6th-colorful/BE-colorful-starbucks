package colorful.starbucks.admin.infrastructure;

import colorful.starbucks.admin.domain.ProductTopCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTopCategoryRepository extends JpaRepository<ProductTopCategory, Long> {
}
