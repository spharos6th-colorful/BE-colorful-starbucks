package colorful.starbucks.event.application;

import colorful.starbucks.event.dto.request.EventProductCreateRequestDto;
import colorful.starbucks.event.dto.request.EventProductFilterRequestDto;
import colorful.starbucks.event.dto.response.EventProductCodeResponseDto;
import org.springframework.data.domain.Page;

public interface EventProductService {

    void createEventProduct(EventProductCreateRequestDto eventProductCreateRequestDto);

    Page<EventProductCodeResponseDto> getEventProductCodes(EventProductFilterRequestDto eventProductFilterRequestDto);
}
