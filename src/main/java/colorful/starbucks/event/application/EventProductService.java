package colorful.starbucks.event.application;

import colorful.starbucks.event.dto.request.EventProductCreateRequestDto;
import colorful.starbucks.event.dto.request.EventProductCodesRequestDto;
import colorful.starbucks.event.dto.response.EventProductCodesResponseDto;
import org.springframework.data.domain.Page;

public interface EventProductService {

    void createEventProduct(EventProductCreateRequestDto eventProductCreateRequestDto);

    Page<EventProductCodesResponseDto> getEventProductCodes(EventProductCodesRequestDto eventProductCodesRequestDto);
}
