package colorful.starbucks.admin.infrastructure;

import colorful.starbucks.admin.domain.ProductCategoryList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductReadRepository extends JpaRepository<ProductCategoryList, Long>, ProductReadRepositoryCustom {
    Optional<ProductCategoryList> findByProductCode(Long key);
}
