package colorful.starbucks.payments.infrastructure;

import colorful.starbucks.payments.domain.PaymentHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentHistoryRepository extends JpaRepository<PaymentHistory, Long> {
    Optional<PaymentHistory> findByPaymentsNumber(String paymentsNumber);

    List<PaymentHistory> findByMemberUuidOrderByCreatedAtDesc(String memberUuid);

    List<PaymentHistory> findByOrderCodeAndMemberUuid(Long orderCode, String memberUuid);

}
