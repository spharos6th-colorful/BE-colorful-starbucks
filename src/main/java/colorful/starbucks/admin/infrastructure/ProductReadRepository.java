package colorful.starbucks.admin.infrastructure;

import colorful.starbucks.admin.domain.ProductCategoryList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductReadRepository extends JpaRepository<ProductCategoryList, Long>, ProductReadRepositoryCustom {
}
