package colorful.starbucks.order.presentation;

import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.common.util.CursorPage;
import colorful.starbucks.order.application.OrderDetailService;
import colorful.starbucks.order.domain.OrderDetail;
import colorful.starbucks.order.dto.OrderDetailFilterDto;
import colorful.starbucks.order.dto.response.OrderDetailCursorResponseDto;
import colorful.starbucks.order.vo.OrderDetailFilterVo;
import colorful.starbucks.order.vo.response.OrderDetailCursorResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order-details")
@RequiredArgsConstructor
public class OrderDetailController {
    private final OrderDetailService orderDetailService;

    @GetMapping
    public ApiResponse<CursorPage<OrderDetailCursorResponseVo>> getOrderDetailList(Authentication authentication,
                                                                                   @ModelAttribute OrderDetailFilterVo orderDetailFilterVo) {
        String memberUuid = authentication.getName();

        OrderDetailFilterDto dto = OrderDetailFilterDto.of(orderDetailFilterVo, memberUuid);

        CursorPage<OrderDetailCursorResponseVo> result = orderDetailService.getOrderDetailList(dto)
                .map(OrderDetailCursorResponseDto::from)
                .map(OrderDetailCursorResponseDto::toVo);

        return ApiResponse.ok("주문 상세 목록 조회 성공", result);
    }

}

