package colorful.starbucks.delivery.presentation;

import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.delivery.application.DeliveryService;
import colorful.starbucks.delivery.dto.request.DeliveryAddRequestDto;
import colorful.starbucks.delivery.dto.request.DeliveryAddressEditRequestDto;
import colorful.starbucks.delivery.dto.request.DeliveryDeleteRequestDto;
import colorful.starbucks.delivery.dto.request.DeliveryAddressRequestDto;
import colorful.starbucks.delivery.dto.response.DeliveryDefaultAddressResponseDto;
import colorful.starbucks.delivery.vo.request.DeliveryAddRequestVo;

import colorful.starbucks.delivery.vo.request.DeliveryAddressEditRequestVo;
import colorful.starbucks.delivery.vo.response.DeliveryAddressResponseVo;
import colorful.starbucks.delivery.vo.response.DeliveryDefaultAddressResponseVo;
import lombok.RequiredArgsConstructor;
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
        deliveryService.addAddress(DeliveryAddRequestDto.from(deliveryAddRequestVo, authentication.getName()));
        return ApiResponse.ok("배송지 추가가 완료 되었습니다.",
                null);
    }
    @DeleteMapping("/address/{memberAddressUuid}")
    public ApiResponse<Void> deleteAddress(Authentication authentication,
                                           @PathVariable String memberAddressUuid) {
        deliveryService.deleteAddress(DeliveryDeleteRequestDto.of(authentication.getName(), memberAddressUuid));
        return ApiResponse.ok("배송지 삭제가 완료 되었습니다.",
                null);
    }
    @GetMapping("/{memberAddressUuid}")
    public ApiResponse<DeliveryAddressResponseVo> getIndividualAddress(Authentication authentication,
                                                                       @PathVariable String memberAddressUuid) {

        return ApiResponse.ok("개별 배송지 조회가 완료 되었습니다.",
                deliveryService.getIndividualAddress(DeliveryAddressRequestDto.of(authentication.getName(), memberAddressUuid)).toVo());
    }
    @PatchMapping("/address/{memberAddressUuid}")
    public ApiResponse<Void> editDeliveryAddress(Authentication authentication,
                                                 @PathVariable String memberAddressUuid,
                                                 @RequestBody DeliveryAddressEditRequestVo deliveryAddressEditRequestVo) {
        deliveryService.editAddress(DeliveryAddressEditRequestDto.from(deliveryAddressEditRequestVo, authentication.getName(), memberAddressUuid));
        return ApiResponse.ok("배송지 수정이 완료 되었습니다.",null);
    }

    @GetMapping("/default-address")
    public ApiResponse<DeliveryDefaultAddressResponseVo> getDefaultAddress(Authentication authentication) {

        return ApiResponse.ok("기본 배송지 조회가 완료 되었습니다.",
                deliveryService.getDefaultAddress(authentication.getName()).toVo());
    }




}
