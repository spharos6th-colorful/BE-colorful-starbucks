package colorful.starbucks.event.infrastructure;

import colorful.starbucks.event.domain.EventProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventProductRepository extends JpaRepository<EventProduct, Long>, EventProductRepositoryCustom {
}
