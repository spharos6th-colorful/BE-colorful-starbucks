package colorful.starbucks.summary.infrastructure;

import colorful.starbucks.summary.domain.MemberOrderSummary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberOrderSummaryRepository extends JpaRepository<MemberOrderSummary, String> {

    Optional<MemberOrderSummary> findByMemberUuid(String memberUuid);
}
