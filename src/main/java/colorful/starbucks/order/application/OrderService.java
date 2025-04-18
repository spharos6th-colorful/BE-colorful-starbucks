package colorful.starbucks.order.application;

import colorful.starbucks.common.util.CursorPage;
import colorful.starbucks.order.dto.OrderListFilterDto;
import colorful.starbucks.order.dto.PreOrderDto;
import colorful.starbucks.order.dto.request.OrderCancelRequestDto;
import colorful.starbucks.order.dto.request.OrderCreateRequestDto;
import colorful.starbucks.order.dto.response.OrderCreateResponseDto;
import colorful.starbucks.order.dto.response.OrderCursorResponseDto;

public interface OrderService {

    OrderCreateResponseDto createOrder(OrderCreateRequestDto orderCreateRequestDto);

    CursorPage<OrderCursorResponseDto> getOrderList(OrderListFilterDto orderListFilterDto);

    OrderCancelRequestDto cancelOrder(OrderCancelRequestDto orderCancelRequestDto);

    PreOrderDto createPreOrder(OrderCreateRequestDto orderCreateRequestDto);


}
