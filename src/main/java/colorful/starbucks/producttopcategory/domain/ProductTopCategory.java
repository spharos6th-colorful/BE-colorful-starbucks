package colorful.starbucks.producttopcategory.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductTopCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_top_category_id")
    private Long id;

    private String categoryName;

    @Builder
    private ProductTopCategory(Long id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }
}
