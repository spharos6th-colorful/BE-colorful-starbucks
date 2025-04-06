package colorful.starbucks.order.application;

import colorful.starbucks.order.dto.OrderListFilterDto;
import colorful.starbucks.order.dto.request.OrderCreateRequestDto;
import colorful.starbucks.order.dto.response.OrderCreateResponseDto;
import colorful.starbucks.order.dto.response.OrderCursorResponseDto;

import java.util.List;

public interface OrderService {

    OrderCreateResponseDto createOrder(OrderCreateRequestDto orderCreateRequestDto);

    List<OrderCursorResponseDto> getOrderList(OrderListFilterDto orderListFilterDto);

}
