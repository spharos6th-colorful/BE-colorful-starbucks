package colorful.starbucks.member.infrastructure;

import colorful.starbucks.member.domain.Terms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermsRepository extends JpaRepository<Terms, Long> {
}
