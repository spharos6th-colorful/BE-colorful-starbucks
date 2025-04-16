package colorful.starbucks.product.infrastructure;

import colorful.starbucks.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByProductCodeAndIsDeletedIsFalse(Long productCode);

    @Query("SELECT p FROM Product p " +
            "WHERE p.isDeleted = false " +
            "AND p.createdAt BETWEEN :startDate AND :endDate")
    List<Product> findNewProducts(LocalDateTime startDate, LocalDateTime endDate);
}
