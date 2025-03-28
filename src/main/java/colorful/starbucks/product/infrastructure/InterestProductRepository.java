package colorful.starbucks.product.infrastructure;

import colorful.starbucks.product.domain.InterestProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InterestProductRepository extends JpaRepository<InterestProduct, Long> {

    void deleteByMemberUuidAndProductCode(String memberUuid, String productCode);

    boolean existsInterestProductByMemberUuidAndProductCode(String memberUuid, String productCode);

    @Query(value = "select ip from InterestProduct ip " +
            "where ip.memberUuid = :memberUuid and ip.isDeleted = false",
            countQuery = "select count(ip) from InterestProduct ip " +
                    "where ip.memberUuid = :memberUuid " +
                    "and ip.isDeleted = false")
    Page<InterestProduct> findAllByMemberUuid(String memberUuid, Pageable pageable);
}
