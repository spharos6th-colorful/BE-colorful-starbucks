package colorful.starbucks.delivery.application;

import colorful.starbucks.delivery.dto.request.DeliveryAddRequestDto;
import colorful.starbucks.delivery.dto.request.DeliveryAddressEditRequestDto;
import colorful.starbucks.delivery.dto.request.DeliveryDeleteRequestDto;
import colorful.starbucks.delivery.dto.request.DeliveryAddressRequestDto;
import colorful.starbucks.delivery.dto.response.DeliveryAddressResponseDto;
import colorful.starbucks.delivery.dto.response.DeliveryDefaultAddressResponseDto;

public interface DeliveryService {

    void addAddress(DeliveryAddRequestDto deliveryAddRequestDto);
    void deleteAddress(DeliveryDeleteRequestDto deliveryDeleteRequestDto);
    DeliveryAddressResponseDto getIndividualAddress(DeliveryAddressRequestDto deliveryAddressRequestDto);
    void editAddress(DeliveryAddressEditRequestDto deliveryAddressEditRequestDto);
    DeliveryDefaultAddressResponseDto getDefaultAddress(String memberUuid);
}
