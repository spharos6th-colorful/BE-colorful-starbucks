package colorful.starbucks.delivery.application;

import colorful.starbucks.delivery.dto.request.DeliveryAddRequestDto;
import colorful.starbucks.delivery.dto.request.DeliveryDeleteRequestDto;

public interface DeliveryService {

    void addAddress(String memberUuid, DeliveryAddRequestDto deliveryAddRequestDto);
    void deleteAddress(String memberUuid, DeliveryDeleteRequestDto deliveryDeleteRequestDto);
}
