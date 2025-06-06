package colorful.starbucks.cart.infrastructure;

import colorful.starbucks.cart.domain.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    void deleteById(Long id);

    Page<Cart> findAllByMemberUuidAndIsDeletedIsFalse(String memberUuid, Pageable pageable);

    Optional<Cart> findByMemberUuidAndProductDetailCodeAndIsDeletedIsFalse(String memberUuid, Long productDetailCode);

    Optional<Cart> findByIdAndMemberUuid(Long id, String memberUuid);

    void deleteAllByMemberUuid(String memberUuid);

    Optional<Object> deleteByIdAndMemberUuid(Long id, String memberUuid);

    Cart findAllByMemberUuid(String memberUuid);

    @Modifying
    @Query("UPDATE Cart c SET c.checked = :checked WHERE c.memberUuid = :memberUuid")
    void updateCheckedByMemberUuid(String memberUuid, Boolean checked);

    @Modifying
    @Query("DELETE FROM Cart c " +
            "WHERE c.memberUuid = :memberUuid AND c.productDetailCode IN :productDetailCodes " +
            "AND c.isDeleted = false")
    void deleteCartsAfterOrder(String memberUuid, List<Long> productDetailCodes);

}
