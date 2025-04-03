package colorful.starbucks.event.infrastructure;

import colorful.starbucks.event.domain.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventRepositoryCustom {

    Page<Event> getEvents(Pageable pageable);
}
