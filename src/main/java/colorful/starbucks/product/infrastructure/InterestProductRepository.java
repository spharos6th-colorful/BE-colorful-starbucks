package colorful.starbucks.product.infrastructure;

import colorful.starbucks.product.domain.InterestProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InterestProductRepository extends JpaRepository<InterestProduct, Long> {

    @Query(countQuery = "SELECT count(ip) FROM InterestProduct ip " +
            "WHERE ip.memberUuid = :memberUuid " +
            "AND ip.isDeleted = false")
    Page<InterestProduct> findAllByMemberUuidAndIsDeletedIsFalse(String memberUuid, Pageable pageable);

    void deleteByIdAndMemberUuid(Long interestProductId, String memberUuid);
}
