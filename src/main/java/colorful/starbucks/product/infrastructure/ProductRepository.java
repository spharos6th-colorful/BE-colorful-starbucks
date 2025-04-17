package colorful.starbucks.product.infrastructure;

import colorful.starbucks.product.domain.Product;
import colorful.starbucks.search.dto.ProductSearchDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByProductCodeAndIsDeletedIsFalse(Long productCode);

    @Query("SELECT new colorful.starbucks.search.dto.ProductSearchDto(p.id, p.productCode, p.productName, pc.topCategoryName, pc.bottomCategoryName, p.price, p.createdAt) " +
            "FROM Product p JOIN ProductCategoryList pc ON p.productCode = pc.productCode")
    List<ProductSearchDto> findAllForSearch();
}
