package colorful.starbucks.event.infrastructure;

import colorful.starbucks.common.util.CursorPage;
import colorful.starbucks.event.dto.request.EventProductCodesRequestDto;

public interface EventProductRepositoryCustom {

    CursorPage<Long> getEventProductCodesByEventUuid(EventProductCodesRequestDto eventProductCodesRequestDto);
}
