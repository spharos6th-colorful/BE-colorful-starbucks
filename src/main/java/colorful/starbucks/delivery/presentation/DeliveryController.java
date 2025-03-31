package colorful.starbucks.delivery.presentation;

import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.delivery.application.DeliveryService;
import colorful.starbucks.delivery.dto.request.DeliveryAddRequestDto;
import colorful.starbucks.delivery.vo.request.DeliveryAddRequestVo;

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
        return ApiResponse.ok("배송지 추가가 완료 되었습니다.",
                null);
    }
    @DeleteMapping("/address/{memberAddressUuid}")
    public ApiResponse<Void> deleteAddress(Authentication authentication, @PathVariable String memberAddressUuid) {
        deliveryService.deleteAddress(authentication.getName(), memberAddressUuid);
        return ApiResponse.ok("배송지 삭제가 완료 되었습니다.",
                null);
    }



}
