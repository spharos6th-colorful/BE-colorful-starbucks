package colorful.starbucks.order.infrastructure;

import colorful.starbucks.order.dto.OrderListFilterDto;
import colorful.starbucks.order.dto.response.OrderListResponseDto;

import java.util.List;

public interface OrderRepositoryCustom {

    List<OrderListResponseDto> getOrderList(OrderListFilterDto orderListFilterDto);

}
