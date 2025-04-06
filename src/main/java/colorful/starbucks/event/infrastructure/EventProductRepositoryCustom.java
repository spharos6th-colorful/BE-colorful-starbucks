package colorful.starbucks.event.infrastructure;

import org.springframework.data.domain.Page;

public interface EventProductRepositoryCustom {

    Page<Long> getEventProducts(String eventUuid, Integer page, Integer size);
}
