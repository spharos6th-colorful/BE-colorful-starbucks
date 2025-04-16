package colorful.starbucks.product.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NewProduct {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("상품 코드")
    @Column(nullable = false, unique = true)
    private Long productCode;

    @Comment("상품 생성 일자")
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Builder
    private NewProduct(Long id, Long productCode, LocalDateTime createdAt) {
        this.id = id;
        this.productCode = productCode;
        this.createdAt = createdAt;
    }
}
