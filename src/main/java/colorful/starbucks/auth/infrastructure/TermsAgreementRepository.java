package colorful.starbucks.auth.infrastructure;

import colorful.starbucks.auth.domain.TermsAgreement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermsAgreementRepository extends JpaRepository<TermsAgreement, Long> {
}
