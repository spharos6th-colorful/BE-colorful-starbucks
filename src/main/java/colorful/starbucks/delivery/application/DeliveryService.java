package colorful.starbucks.delivery.application;

import colorful.starbucks.delivery.dto.request.*;
import colorful.starbucks.delivery.dto.response.DeliveryAddressResponseDto;
import colorful.starbucks.delivery.dto.response.DeliveryAddressListDto;
import colorful.starbucks.delivery.dto.response.DeliveryDefaultAddressResponseDto;

import java.util.List;

public interface DeliveryService {

    void addAddress(DeliveryAddRequestDto deliveryAddRequestDto);
    void deleteAddress(DeliveryDeleteRequestDto deliveryDeleteRequestDto);
    DeliveryAddressResponseDto getIndividualAddress(DeliveryAddressRequestDto deliveryAddressRequestDto);
    void editAddress(DeliveryAddressEditRequestDto deliveryAddressEditRequestDto);
    DeliveryDefaultAddressResponseDto getDefaultAddress(String memberUuid);
    List<DeliveryAddressResponseDto> getAddressList(String memberUuid);
    void editDefaultAddress(List<DeliveryDefaultAddressRequestDto> deliveryDefaultAddressRequestDtos);
    void deleteAllAddresses(String memberUuid);
}
