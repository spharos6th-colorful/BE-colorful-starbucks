package colorful.starbucks.cart.infrastructure;

import colorful.starbucks.cart.domain.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    void deleteById(Long id);

    @Query(value = "SELECT * FROM Cart cp WHERE cp.member_uuid = :memberUuid AND cp.is_deleted = FALSE",
        countQuery = "SELECT COUNT(*) from Cart cp WHERE cp.member_uuid = :memberUuid AND cp.is_deleted = FALSE",
        nativeQuery = true)
    Page<Cart> findAllByMemberUuid(String memberUuid, Pageable pageable);

    Optional<Cart> findByMemberUuidAndId(String memberUuid, Long id);

    Optional<Cart> findByMemberUuidAndProductDetailCodeAndIsDeletedIsFalse(String memberUuid, String productDetailCode);

    Optional<Cart> findByIdAndMemberUuid(Long id, String memberUuid);
}
