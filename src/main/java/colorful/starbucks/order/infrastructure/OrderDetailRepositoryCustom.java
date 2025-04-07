package colorful.starbucks.order.infrastructure;

import colorful.starbucks.common.util.CursorPage;
import colorful.starbucks.order.dto.OrderDetailFilterDto;
import colorful.starbucks.order.dto.response.OrderDetailCursorResponseDto;

public interface OrderDetailRepositoryCustom {

    CursorPage<OrderDetailCursorResponseDto> getOrderDetailList(OrderDetailFilterDto orderDetailFilterDto);
}
