package colorful.starbucks.delivery.application;

import colorful.starbucks.delivery.dto.request.DeliveryAddRequestDto;
import colorful.starbucks.delivery.dto.request.DeliveryAddressEditRequestDto;
import colorful.starbucks.delivery.dto.response.DeliveryIndividualAddressResponseDto;

public interface DeliveryService {

    void addAddress(DeliveryAddRequestDto deliveryAddRequestDto);
    void deleteAddress(String memberUuid, String memberAddressUuid);
    DeliveryIndividualAddressResponseDto getIndividualAddress(String memberUuid, String memberAddressUuid);
    void editAddress(String memberUuid, String memberAddressUuid, DeliveryAddressEditRequestDto deliveryAddressEditRequestDto);
}
