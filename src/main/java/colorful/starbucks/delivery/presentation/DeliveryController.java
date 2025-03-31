package colorful.starbucks.delivery.presentation;

import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.delivery.application.DeliveryService;
import colorful.starbucks.delivery.dto.request.DeliveryAddRequestDto;
import colorful.starbucks.delivery.vo.request.DeliveryAddRequestVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/delivery")
@RestController
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping("/address")
    public ApiResponse<Void> addAddress(Authentication authentication,
                                                         @RequestBody DeliveryAddRequestVo deliveryAddRequestVo) {
        deliveryService.addAddress(authentication.getName(), DeliveryAddRequestDto.from(deliveryAddRequestVo));
        return ApiResponse.of(HttpStatus.OK, "배송지 추가가 완료 되었습니다.",
                null);
    }

}
