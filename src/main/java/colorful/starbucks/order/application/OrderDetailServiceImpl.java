package colorful.starbucks.order.application;

import colorful.starbucks.common.util.CursorPage;
import colorful.starbucks.order.domain.Order;
import colorful.starbucks.order.domain.OrderDetail;
import colorful.starbucks.order.dto.OrderDetailFilterDto;
import colorful.starbucks.order.dto.request.OrderDetailCreateRequestDto;
import colorful.starbucks.order.dto.response.OrderDetailCursorResponseDto;
import colorful.starbucks.order.infrastructure.OrderDetailRepository;
import colorful.starbucks.product.application.ProductDetailService;
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
    private final ProductDetailService productDetailService;


    @Transactional
    @Override
    public List<OrderDetail> saveAllDetails(Order order, List<OrderDetailCreateRequestDto> orderDetailCreateRequestDto) {

        List<OrderDetail> orderDetails = orderDetailCreateRequestDto.stream()
                .map(orderDetailRequestDto -> orderDetailRequestDto.toEntity(order))
                .collect(Collectors.toList());

        orderDetailRepository.saveAll(orderDetails);

        return orderDetails;
    }



    @Override
    public CursorPage<OrderDetailCursorResponseDto> getOrderDetailList(OrderDetailFilterDto orderDetailFilterDto) {
        return orderDetailRepository.getOrderDetailList(orderDetailFilterDto)
                .map(OrderDetailCursorResponseDto::from);
    }

}