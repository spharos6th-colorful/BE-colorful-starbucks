package colorful.starbucks.product.infrastructure;

import colorful.starbucks.product.domain.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long>, ProductDetailRepositoryCustom {

    List<ProductDetail> findAllByProductCodeAndIsDeletedIsFalse(String productCode);

    boolean existsByProductCodeAndSizeIdAndColorIdAndIsDeletedIsFalse(
            String productCode, Long sizeId, Long colorId
    );

    Optional<ProductDetail> findByProductDetailCodeAndIsDeletedIsFalse(Long productDetailCode);

}
