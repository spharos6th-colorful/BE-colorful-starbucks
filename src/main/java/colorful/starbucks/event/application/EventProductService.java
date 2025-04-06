package colorful.starbucks.event.application;

import colorful.starbucks.event.dto.request.EventProductCreateRequestDto;

public interface EventProductService {

    void createEventProduct(EventProductCreateRequestDto eventProductCreateRequestDto);
}
