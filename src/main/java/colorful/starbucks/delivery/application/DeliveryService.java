package colorful.starbucks.delivery.application;

import colorful.starbucks.delivery.dto.request.DeliveryAddRequestDto;

public interface DeliveryService {

    void addAddress(DeliveryAddRequestDto deliveryAddRequestDto);
    void deleteAddress(String memberUuid, String memberAddressUuid);
}
