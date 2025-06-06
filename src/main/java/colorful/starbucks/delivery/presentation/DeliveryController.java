package colorful.starbucks.delivery.presentation;

import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.delivery.application.DeliveryService;
import colorful.starbucks.delivery.dto.request.*;
import colorful.starbucks.delivery.dto.response.DeliveryAddressListDto;
import colorful.starbucks.delivery.vo.request.DeliveryAddRequestVo;
import colorful.starbucks.delivery.vo.request.DeliveryAddressEditRequestVo;
import colorful.starbucks.delivery.vo.request.DeliveryDefaultAddressRequestVo;
import colorful.starbucks.delivery.vo.response.DeliveryAddressListVo;
import colorful.starbucks.delivery.vo.response.DeliveryAddressResponseVo;
import colorful.starbucks.delivery.vo.response.DeliveryDefaultAddressResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/delivery")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @Operation(
            summary = "배송지 추가 API",
            description = "배송지를 추가하는 API 입니다.",
            tags = {"DELIVERY-SERVICE"}
    )
    @PostMapping("/address")
    public ApiResponse<Void> addAddress(Authentication authentication,
                                        @RequestBody DeliveryAddRequestVo deliveryAddRequestVo) {
        deliveryService.addAddress(DeliveryAddRequestDto.of(deliveryAddRequestVo, authentication.getName()));
        return ApiResponse.ok("배송지 추가가 완료 되었습니다.",
                null);
    }

    @Operation(
            summary = "배송지 기본 주소 변경 API",
            description = "배송지 기본 주소를 변경하는 API 입니다.",
            tags = {"DELIVERY-SERVICE"}
    )
    @PutMapping("/default-address")
    public ApiResponse<Void> changeDefaultAddress(Authentication authentication,
                                                  @RequestBody List<DeliveryDefaultAddressRequestVo> deliveryDefaultAddressRequestVos) {

        deliveryService.editDefaultAddress(DeliveryDefaultAddressRequestDto.of(deliveryDefaultAddressRequestVos, authentication.getName()));
        return ApiResponse.ok("기본 배송지로 변경 완료 했습니다.", null);

    }

    @Operation(
            summary = "배송지 수정 API",
            description = "배송지를 수정하는 API 입니다.",
            tags = {"DELIVERY-SERVICE"}
    )
    @PutMapping("/address/{memberAddressUuid}")
    public ApiResponse<Void> editDeliveryAddress(Authentication authentication,
                                                 @PathVariable String memberAddressUuid,
                                                 @RequestBody DeliveryAddressEditRequestVo deliveryAddressEditRequestVo) {

        deliveryService.editAddress(DeliveryAddressEditRequestDto.of(deliveryAddressEditRequestVo, authentication.getName(), memberAddressUuid));
        return ApiResponse.ok("배송지 수정이 완료 되었습니다.", null);
    }


    @Operation(
            summary = "배송지 단건 조회 API",
            description = "배송지를 단건 조회하는 API 입니다.",
            tags = {"DELIVERY-SERVICE"}
    )
    @GetMapping("/{memberAddressUuid}")
    public ApiResponse<DeliveryAddressResponseVo> getIndividualAddress(Authentication authentication,
                                                                       @PathVariable String memberAddressUuid) {

        return ApiResponse.ok("개별 배송지 조회가 완료 되었습니다.",
                deliveryService.getIndividualAddress(DeliveryAddressRequestDto.of(authentication.getName(), memberAddressUuid)).toVo());
    }

    @Operation(
            summary = "기본 배송지 조회 API",
            description = "기본 배송지를 조회하는 API 입니다.",
            tags = {"DELIVERY-SERVICE"}
    )
    @GetMapping("/default-address")
    public ApiResponse<DeliveryDefaultAddressResponseVo> getDefaultAddress(Authentication authentication) {

        return ApiResponse.ok("기본 배송지 조회가 완료 되었습니다.",
                deliveryService.getDefaultAddress(authentication.getName()).toVo());
    }

    @Operation(
            summary = "배송지 목록 조회 API",
            description = "배송지 목록을 조회하는 API 입니다.",
            tags = {"DELIVERY-SERVICE"}
    )
    @GetMapping("/addresses")
    public ApiResponse<DeliveryAddressListVo> getAddressList(Authentication authentication) {

        return ApiResponse.ok("배송지 목록 조회가 완료 되었습니다.",
                DeliveryAddressListDto.from(
                        deliveryService.getAddressList(authentication.getName())).toVo());

    }

    @Operation(
            summary = "배송지 삭제 API",
            description = "배송지를 삭제하는 API 입니다.",
            tags = {"DELIVERY-SERVICE"}
    )
    @DeleteMapping("/address/{memberAddressUuid}")
    public ApiResponse<Void> deleteAddress(Authentication authentication,
                                           @PathVariable String memberAddressUuid) {
        deliveryService.deleteAddress(DeliveryDeleteRequestDto.of(authentication.getName(), memberAddressUuid));
        return ApiResponse.ok("배송지 삭제가 완료 되었습니다.",
                null);
    }

    @Operation(
            summary = "배송지 전체 삭제 API",
            description = "배송지를 전체 삭제하는 API 입니다.",
            tags = {"DELIVERY-SERVICE"}
    )
    @DeleteMapping("/addresses")
    public ApiResponse<Void> deleteAllAddresses(Authentication authentication) {
        deliveryService.deleteAllAddresses(authentication.getName());
        return ApiResponse.ok("모든 배송지 삭제가 완료 되었습니다.",
                null);
    }


}
