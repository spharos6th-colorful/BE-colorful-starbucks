package colorful.starbucks.cart.infrastructure;

import colorful.starbucks.cart.domain.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    void deleteById(Long id);

    Page<Cart> findAllByMemberUuidAndIsDeletedIsFalse(String memberUuid, Pageable pageable);

    Optional<Cart> findByMemberUuidAndId(String memberUuid, Long id);

    Optional<Cart> findByMemberUuidAndProductDetailCodeAndIsDeletedIsFalse(String memberUuid, String productDetailCode);

    Optional<Cart> findByIdAndMemberUuid(Long id, String memberUuid);
}
