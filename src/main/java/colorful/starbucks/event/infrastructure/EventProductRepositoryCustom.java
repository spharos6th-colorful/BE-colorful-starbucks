package colorful.starbucks.event.infrastructure;

import org.springframework.data.domain.Page;

public interface EventProductRepositoryCustom {

    Page<Long> getEventProductCodes(String eventUuid, Integer page, Integer size);
}
