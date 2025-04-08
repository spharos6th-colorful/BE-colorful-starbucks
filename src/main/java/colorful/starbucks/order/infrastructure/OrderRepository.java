package colorful.starbucks.order.infrastructure;

import colorful.starbucks.common.util.CursorPage;
import colorful.starbucks.order.domain.Order;
import colorful.starbucks.order.dto.response.OrderCursorResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>,OrderRepositoryCustom {

}
