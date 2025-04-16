package colorful.starbucks.order.presentation;

import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.common.util.CursorPage;
import colorful.starbucks.order.application.OrderService;
import colorful.starbucks.order.dto.OrderListFilterDto;
import colorful.starbucks.order.dto.request.OrderCancelRequestDto;
import colorful.starbucks.order.dto.request.OrderCreateRequestDto;
import colorful.starbucks.order.dto.response.OrderCursorResponseDto;
import colorful.starbucks.order.vo.OrderListFilterVo;
import colorful.starbucks.order.vo.request.OrderCancelRequestVo;
import colorful.starbucks.order.vo.request.OrderCreateRequestVo;
import colorful.starbucks.order.vo.response.OrderCreateResponseVo;
import colorful.starbucks.order.vo.response.OrderCursorResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ApiResponse<OrderCreateResponseVo> createOrder(Authentication authentication,
                                                          @RequestBody OrderCreateRequestVo orderCreateRequestVo) {
        return ApiResponse.ok(
                "주문이 완료되었습니다.",
                orderService
                        .createOrder(OrderCreateRequestDto.of(orderCreateRequestVo, authentication.getName()))
                        .toVo()
        );
    }

    @GetMapping
    public ApiResponse<CursorPage<OrderCursorResponseVo>> getOrderList(Authentication authentication,
                                                                       @ModelAttribute OrderListFilterVo orderListFilterVo) {

        return ApiResponse.ok("주문 목록 조회 성공",
                orderService.getOrderList(OrderListFilterDto.of(orderListFilterVo, authentication.getName()))
                        .map(OrderCursorResponseDto::toVo)
        );
    }

    @PostMapping("/cancel")
    public ApiResponse<Void> cancelOrder(Authentication authentication,
                                         @RequestBody OrderCancelRequestVo orderCancelRequestVo) {

        orderService.cancelOrder(OrderCancelRequestDto.of(orderCancelRequestVo, authentication.getName()));

        return ApiResponse.ok("주문이 성공적으로 취소되었습니다.", null);
    }


}

