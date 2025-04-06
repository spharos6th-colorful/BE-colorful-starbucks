package colorful.starbucks.event.infrastructure;

import org.springframework.data.domain.Page;

public interface EventProductRepositoryCustom {

    Page<Long> getEventProductCodesByEventUuid(String eventUuid, Integer page, Integer size);
}
