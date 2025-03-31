package colorful.starbucks.delivery.infrastructure;

import colorful.starbucks.delivery.domain.DeliveryAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<DeliveryAddress, Long> {
}
