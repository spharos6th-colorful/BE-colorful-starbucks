package colorful.starbucks.order.application;

import colorful.starbucks.common.util.CursorPage;
import colorful.starbucks.order.dto.OrderListFilterDto;
import colorful.starbucks.order.dto.PreOrderDto;
import colorful.starbucks.order.dto.request.OrderCancelRequestDto;
import colorful.starbucks.order.dto.request.OrderCreateRequestDto;
import colorful.starbucks.order.dto.request.OrderExistsRequestDto;
import colorful.starbucks.order.dto.request.PreOrderRequestDto;
import colorful.starbucks.order.dto.response.OrderCreateResponseDto;
import colorful.starbucks.order.dto.response.OrderCursorResponseDto;
import colorful.starbucks.order.dto.response.OrderExistsResponseDto;
import colorful.starbucks.order.vo.request.PreOrderRequestVo;

public interface OrderService {

    OrderCreateResponseDto createOrder(OrderCreateRequestDto orderCreateRequestDto);

    CursorPage<OrderCursorResponseDto> getOrderList(OrderListFilterDto orderListFilterDto);

    OrderCancelRequestDto cancelOrder(OrderCancelRequestDto orderCancelRequestDto);

    PreOrderDto createPreOrder(PreOrderRequestDto preOrderRequestDto);

    OrderExistsResponseDto existsOrder(OrderExistsRequestDto orderExistsRequestDto);
}
