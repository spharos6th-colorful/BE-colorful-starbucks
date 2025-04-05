package colorful.starbucks.order.infrastructure;

import colorful.starbucks.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
