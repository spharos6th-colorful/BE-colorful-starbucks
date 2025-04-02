package colorful.starbucks.admin.infrastructure;

import colorful.starbucks.admin.domain.ProductCategoryList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryListRepository extends JpaRepository<ProductCategoryList, Long> {
}
