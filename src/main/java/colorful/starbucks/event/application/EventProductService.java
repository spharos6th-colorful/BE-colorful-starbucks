package colorful.starbucks.event.application;

import colorful.starbucks.common.util.CursorPage;
import colorful.starbucks.event.dto.request.EventProductCodesRequestDto;
import colorful.starbucks.event.dto.request.EventProductCreateRequestDto;
import colorful.starbucks.event.dto.response.EventProductCodesResponseDto;

import java.util.List;

public interface EventProductService {

    void createEventProduct(List<EventProductCreateRequestDto> eventProductCreateRequestDtos);

    CursorPage<EventProductCodesResponseDto> getEventProductCodes(EventProductCodesRequestDto eventProductCodesRequestDto);
}
