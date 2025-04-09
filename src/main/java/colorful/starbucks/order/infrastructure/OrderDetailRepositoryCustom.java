package colorful.starbucks.order.infrastructure;

import colorful.starbucks.common.util.CursorPage;
import colorful.starbucks.order.domain.OrderDetail;
import colorful.starbucks.order.dto.OrderDetailFilterDto;

public interface OrderDetailRepositoryCustom {

     CursorPage<OrderDetail> getOrderDetailList(OrderDetailFilterDto orderDetailFilterDto);
}
