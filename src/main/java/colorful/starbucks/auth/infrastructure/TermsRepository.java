package colorful.starbucks.auth.infrastructure;

import colorful.starbucks.auth.domain.Terms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermsRepository extends JpaRepository<Terms, Long> {
}
