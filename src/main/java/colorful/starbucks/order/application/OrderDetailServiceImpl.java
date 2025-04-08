package colorful.starbucks.order.application;

import colorful.starbucks.common.util.CursorPage;
import colorful.starbucks.order.domain.Order;
import colorful.starbucks.order.domain.OrderDetail;
import colorful.starbucks.order.dto.OrderDetailFilterDto;
import colorful.starbucks.order.dto.request.OrderCreateDetailRequestDto;
import colorful.starbucks.order.infrastructure.OrderDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderDetailServiceImpl implements OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;

    @Transactional
    @Override
    public List<OrderDetail> saveAllDetails(Order order, List<OrderCreateDetailRequestDto> orderCreateDetailRequestDto) {

        List<OrderDetail> orderDetails = orderCreateDetailRequestDto.stream()
                .map(orderDetailRequestDto -> orderDetailRequestDto.toEntity(order))
                .collect(Collectors.toList());

        orderDetailRepository.saveAll(orderDetails);

        return orderDetails;
    }

    @Transactional
    @Override
    public CursorPage<OrderDetail> getOrderDetailList(OrderDetailFilterDto orderDetailFilterDto) {
        return orderDetailRepository.getOrderDetailList(orderDetailFilterDto);
    }

}