package colorful.starbucks.order.infrastructure;

import colorful.starbucks.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long>,OrderRepositoryCustom {

    Optional<Order> findByOrderCode(Long orderCode);
}
