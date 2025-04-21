package colorful.starbucks.admin.infrastructure;

import colorful.starbucks.admin.domain.ProductTopCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductTopCategoryRepository extends JpaRepository<ProductTopCategory, Long> {
    List<ProductTopCategory> findAllByOrderByIdDesc();
}
