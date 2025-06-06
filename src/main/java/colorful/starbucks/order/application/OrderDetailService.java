package colorful.starbucks.order.application;

import colorful.starbucks.common.util.CursorPage;
import colorful.starbucks.order.domain.Order;
import colorful.starbucks.order.domain.OrderDetail;
import colorful.starbucks.order.dto.OrderDetailFilterDto;
import colorful.starbucks.order.dto.request.OrderDetailCreateRequestDto;
import colorful.starbucks.order.dto.response.OrderDetailResponseDto;

import java.util.List;

public interface OrderDetailService {

    void saveAllDetails(Order order, List<OrderDetailCreateRequestDto> orderDetailCreateRequestDto);

    CursorPage<OrderDetailResponseDto> getOrderDetailList(OrderDetailFilterDto orderDetailFilterDto);

}
