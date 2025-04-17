package colorful.starbucks.order.application;

import colorful.starbucks.common.exception.BaseException;
import colorful.starbucks.common.response.ResponseStatus;
import colorful.starbucks.common.util.CursorPage;
import colorful.starbucks.order.domain.Order;
import colorful.starbucks.order.domain.OrderDetail;
import colorful.starbucks.order.dto.OrderDetailFilterDto;
import colorful.starbucks.order.dto.request.OrderDetailCreateRequestDto;
import colorful.starbucks.order.dto.response.OrderDetailResponseDto;
import colorful.starbucks.order.infrastructure.OrderDetailRepository;
import colorful.starbucks.product.domain.ProductDetail;
import colorful.starbucks.product.infrastructure.ProductDetailRepository;
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
    private final ProductDetailRepository productDetailRepository;


    @Transactional
    @Override
    public List<OrderDetail> saveAllDetails(Order order, List<OrderDetailCreateRequestDto> orderDetailCreateRequestDto) {

        List<OrderDetail> orderDetails = orderDetailCreateRequestDto.stream()
                .map(orderDetailRequestDto -> {
                    ProductDetail productDetail = productDetailRepository.findByProductDetailCodeAndIsDeletedIsFalse(orderDetailRequestDto.getProductDetailCode())
                            .orElseThrow( () -> new BaseException(ResponseStatus.RESOURCE_NOT_FOUND, "상품을 찾을 수 없습니다."));
                   return orderDetailRequestDto.toEntity(order, productDetail);
                })
                .collect(Collectors.toList());

        orderDetailRepository.saveAll(orderDetails);

        return orderDetails;
    }



    @Override
    public CursorPage<OrderDetailResponseDto> getOrderDetailList(OrderDetailFilterDto orderDetailFilterDto) {
        return orderDetailRepository.getOrderDetailList(orderDetailFilterDto)
                .map(OrderDetailResponseDto::from);
    }

}