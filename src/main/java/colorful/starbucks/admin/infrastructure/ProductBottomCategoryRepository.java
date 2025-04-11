package colorful.starbucks.admin.infrastructure;

import colorful.starbucks.admin.domain.ProductBottomCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductBottomCategoryRepository extends JpaRepository<ProductBottomCategory, Long> {

    List<ProductBottomCategory> findAllByProductTopCategoryId(Long topCategoryId);
}
