package colorful.starbucks.admin.domain;

import colorful.starbucks.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductTopCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_top_category_id")
    private Long id;

    @Comment("상위 카테고리 이름")
    @Column(nullable = false, unique = true)
    private String categoryName;

    @Builder
    private ProductTopCategory(Long id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }
}
