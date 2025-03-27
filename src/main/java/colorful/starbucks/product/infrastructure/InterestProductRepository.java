package colorful.starbucks.product.infrastructure;

import colorful.starbucks.product.domain.InterestProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestProductRepository extends JpaRepository<InterestProduct, Long> {

    void deleteByMemberUuidAndProductCode(String memberUuid, String productCode);

    boolean existsInterestProductByMemberUuidAndProductCode(String memberUuid, String productCode);
}
