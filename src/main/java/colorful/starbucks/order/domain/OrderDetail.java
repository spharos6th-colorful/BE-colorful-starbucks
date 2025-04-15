package colorful.starbucks.order.domain;

import colorful.starbucks.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class OrderDetail extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Comment("상품 코드")
    @Column(nullable = false)
    private Long productCode;

    @Comment("상품 상세 코드")
    @Column(nullable = false)
    private Long productDetailCode;

    @Comment("상품 이름")
    @Column(nullable = false)
    private String productName;

    @Comment("색상 ID")
    @Column(nullable = true)
    private Long sizeId;

    @Comment("색상 ID")
    @Column(nullable = true)
    private Long colorId;

    @Comment("수량")
    @Column(nullable = false)
    private int quantity;

    @Comment("가격")
    @Column(nullable = false)
    private int price;

    @Comment("각인 내용")
    @Column(nullable = true)
    private String carvingContent;

    @Comment("카테고리 이름")
    @Column(nullable = false)
    private String categoryName;

    @Comment("각인 여부")
    @Column(nullable = true)
    private Boolean carving;

    @Comment("상품 상세 썸네일 URL")
    @Column(nullable = true)
    private String productDetailThumbnailUrl;

    @Builder
    private OrderDetail(Long id,
                       Order order,
                        Long productCode,
                       Long productDetailCode,
                       String productName,
                       Long sizeId,
                       Long colorId,
                       int quantity,
                       int price,
                       String carvingContent,
                       String categoryName,
                       Boolean carving,
                       String productDetailThumbnailUrl) {
        this.id = id;
        this.order = order;
        this.productCode = productCode;
        this.productDetailCode = productDetailCode;
        this.productName = productName;
        this.sizeId = sizeId;
        this.colorId = colorId;
        this.quantity = quantity;
        this.price = price;
        this.carvingContent = carvingContent;
        this.categoryName = categoryName;
        this.carving = carving;
        this.productDetailThumbnailUrl = productDetailThumbnailUrl;
    }
}


