package colorful.starbucks.member.infrastructure;

import colorful.starbucks.member.domain.InterestProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface InterestProductRepository extends JpaRepository<InterestProduct, Long> {

    Page<InterestProduct> findAllByMemberUuidAndIsDeletedIsFalse(String memberUuid, Pageable pageable);
    Optional<InterestProduct> findByIdAndMemberUuid(Long interestProductId, String memberUuid);
}
