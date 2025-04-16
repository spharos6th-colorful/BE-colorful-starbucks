package colorful.starbucks.member.domain;

import colorful.starbucks.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.SQLDelete;

@Entity
@Getter
@SQLDelete(sql = "Update interest_product SET is_deleted = true WHERE interest_product_id = ?")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InterestProduct extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interest_product_id")
    private Long id;

    @Comment("회원 UUID")
    @Column(nullable = false)
    private String memberUuid;

    @Comment("상품 코드")
    @Column(nullable = false)
    private Long productCode;

    @Comment("상품 이름")
    @Column(nullable = false)
    private String productName;

    @Comment("상품 가격")
    @Column(nullable = false)
    private int price;

    @Comment("상품 썸네일 URL")
    @Column(nullable = false)
    private String productThumbnailUrl;

    @Builder
    private InterestProduct(Long id,
                            String memberUuid,
                            Long productCode,
                            String productName,
                            int price,
                            String productThumbnailUrl) {
        this.id = id;
        this.memberUuid = memberUuid;
        this.productCode = productCode;
        this.productName = productName;
        this.price = price;
        this.productThumbnailUrl = productThumbnailUrl;
    }
}
