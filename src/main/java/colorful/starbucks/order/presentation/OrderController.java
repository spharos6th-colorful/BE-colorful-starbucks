package colorful.starbucks.order.presentation;

import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.order.application.OrderService;
import colorful.starbucks.order.dto.in.OrderCreateRequestDto;
import colorful.starbucks.order.vo.in.OrderCreateRequestVo;
import colorful.starbucks.order.vo.out.OrderCreateResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    }

