package colorful.starbucks.payments.infrastructure;

import colorful.starbucks.payments.domain.PaymentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PaymentHistoryRepository extends JpaRepository<PaymentHistory, Long> {
    Optional<PaymentHistory> findByPaymentsNumber(String paymentsNumber);

    List<PaymentHistory> findByMemberUuidOrderByCreatedAtDesc(String memberUuid);

    List<PaymentHistory> findByOrderCodeAndMemberUuid(String orderCode, String memberUuid);

    @Modifying
    @Query("UPDATE PaymentHistory p " +
            "SET p.paymentsStatus = 'CANCELED', " +
            "    p.cancelReason = :cancelReason, " +
            "    p.canceledAt = :canceledAt " +
            "WHERE p.paymentsNumber = :paymentsKey")
    void updatePaymentHistory(String cancelReason, String paymentsKey, String canceledAt);
}
