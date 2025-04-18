package colorful.starbucks.order.infrastructure;

import colorful.starbucks.common.util.CursorPage;
import colorful.starbucks.order.domain.Order;
import colorful.starbucks.order.dto.OrderListFilterDto;

public interface OrderRepositoryCustom {

    CursorPage<Order> getOrderList(OrderListFilterDto orderListFilterDto);


}
