package colorful.starbucks.admin.infrastructure;

import colorful.starbucks.admin.domain.ProductBottomCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductBottomCategoryRepository extends JpaRepository<ProductBottomCategory, Long> {
}
