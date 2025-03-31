package colorful.starbucks.delivery.presentation;

import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.delivery.application.DeliveryService;
import colorful.starbucks.delivery.dto.request.DeliveryAddRequestDto;
import colorful.starbucks.delivery.dto.request.DeliveryDeleteRequestDto;
import colorful.starbucks.delivery.vo.request.DeliveryAddRequestVo;
import colorful.starbucks.delivery.vo.request.DeliveryDeleteRequestVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
    @DeleteMapping("/address")
    public ApiResponse<Void> deleteAddress(Authentication authentication, @RequestBody DeliveryDeleteRequestVo deliveryDeleteRequestVo) {
        deliveryService.deleteAddress(authentication.getName(), DeliveryDeleteRequestDto.from(deliveryDeleteRequestVo));
        return ApiResponse.of(HttpStatus.OK, "배송지 삭제가 완료 되었습니다.",
                null);
    }



}
