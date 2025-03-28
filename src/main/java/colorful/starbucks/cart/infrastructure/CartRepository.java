package colorful.starbucks.cart.infrastructure;

import colorful.starbucks.cart.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {


    void deleteByMemberUuidAndId(String memberUuid, Long Id);
}
