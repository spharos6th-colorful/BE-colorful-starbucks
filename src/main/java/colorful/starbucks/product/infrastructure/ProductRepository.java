package colorful.starbucks.product.infrastructure;

import colorful.starbucks.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {
    Optional<Product> findByProductCodeAndIsDeletedIsFalse(Long productCode);
}
