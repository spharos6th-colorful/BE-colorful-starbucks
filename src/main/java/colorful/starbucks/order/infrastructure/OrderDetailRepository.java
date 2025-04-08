package colorful.starbucks.order.infrastructure;

import colorful.starbucks.order.domain.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>,OrderDetailRepositoryCustom{

}
