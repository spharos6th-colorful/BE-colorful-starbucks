package colorful.starbucks.event.application;

import colorful.starbucks.event.dto.request.EventProductCreateRequestDto;
import colorful.starbucks.event.dto.request.EventProductCodesRequestDto;
import colorful.starbucks.event.dto.response.EventProductCodesResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EventProductService {

    void createEventProduct(List<EventProductCreateRequestDto> eventProductCreateRequestDtos);

    Page<EventProductCodesResponseDto> getEventProductCodes(EventProductCodesRequestDto eventProductCodesRequestDto);
}
