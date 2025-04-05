package colorful.starbucks.event.infrastructure;


import colorful.starbucks.event.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long>, EventRepositoryCustom {
    Optional<Event> findByEventUuid(String eventUuid);

    void deleteByEventUuid(String eventUuid);
}
