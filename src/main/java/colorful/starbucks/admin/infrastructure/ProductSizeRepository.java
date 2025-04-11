package colorful.starbucks.admin.infrastructure;

import colorful.starbucks.admin.domain.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSizeRepository extends JpaRepository<Size, Long> {
}
