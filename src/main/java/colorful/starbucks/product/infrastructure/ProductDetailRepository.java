package colorful.starbucks.product.infrastructure;

import colorful.starbucks.product.domain.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long>, ProductDetailRepositoryCustom {

    @Query(value = "select pd from ProductDetail pd " +
            "where pd.productCode = :productCode and pd.isDeleted = false")
    List<ProductDetail> findAllByProductCode(String productCode);

    boolean existsByProductCodeAndSizeIdAndColorIdAndIsDeletedFalse(
            String productCode, Long sizeId, Long colorId
    );

    Optional<ProductDetail> findByProductDetailCode(String productDetailCode);
}
