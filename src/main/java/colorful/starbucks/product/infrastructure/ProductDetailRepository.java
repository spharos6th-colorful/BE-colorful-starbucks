package colorful.starbucks.product.infrastructure;

import colorful.starbucks.product.domain.ProductDetail;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long>, ProductDetailRepositoryCustom {

    List<ProductDetail> findAllByProductCodeAndIsDeletedIsFalse(Long productCode);

    boolean existsByProductCodeAndSizeIdAndColorIdAndIsDeletedIsFalse(
            Long productCode, Long sizeId, Long colorId
    );


    Optional<ProductDetail> findByProductDetailCodeAndIsDeletedIsFalse(Long productDetailCode);

}
