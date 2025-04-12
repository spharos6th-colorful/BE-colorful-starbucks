package colorful.starbucks.admin.infrastructure;

import colorful.starbucks.admin.domain.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductColorRepository extends JpaRepository<Color, Long> {
}
