package colorful.starbucks.delivery.application;

import colorful.starbucks.delivery.dto.request.DeliveryAddRequestDto;

public interface DeliveryService {

    void addAddress(String memberUuid, DeliveryAddRequestDto deliveryAddRequestDto);

}
