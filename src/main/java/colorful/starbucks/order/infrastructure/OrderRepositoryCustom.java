package colorful.starbucks.order.infrastructure;

import colorful.starbucks.common.util.CursorPage;
import colorful.starbucks.order.dto.OrderListFilterDto;
import colorful.starbucks.order.dto.response.OrderCursorResponseDto;

public interface OrderRepositoryCustom {

    CursorPage<OrderCursorResponseDto> getOrderList(OrderListFilterDto orderListFilterDto);


}
