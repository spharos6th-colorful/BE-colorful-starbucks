package colorful.starbucks.cart.infrastructure;

import colorful.starbucks.cart.domain.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartRepository extends JpaRepository<Cart, Long> {

    void deleteByMemberUuidAndId(String memberUuid, Long id);
    @Query(value = "select cp from Cart cp " +
                "where cp.memberUuid = :memberUuid and cp.isDeleted = false",
                countQuery = "select count(cp) from Cart cp "+
                        "where cp.memberUuid = :memberUuid " +
                        "and cp.isDeleted = false"
    )
    Page<Cart> findAllByMemberUuid(String memberUuid, Pageable pageable);
}
