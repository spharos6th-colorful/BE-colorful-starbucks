package colorful.starbucks.summary.infrastructure;

import colorful.starbucks.summary.domain.MemberOrderSummary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberOrderSummaryRepository extends JpaRepository<MemberOrderSummary, String> {

    List<MemberOrderSummary> findByMemberUuid(String memberUuid);
}
