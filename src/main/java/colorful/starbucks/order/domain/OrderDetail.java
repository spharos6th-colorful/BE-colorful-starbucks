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
    @Column(name = "order_detail_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Comment("상품 코드")
    @Column(nullable = false)
    private String productCode;

    @Comment("상품 상세 코드")
    @Column(nullable = false)
    private String productDetailCode;

    @Comment("상품 이름")
    @Column(nullable = false)
    private String productName;

    @Comment("사이즈")
    @Column(nullable = false)
    private String size;

    @Comment("색상")
    @Column(nullable = false)
    private String color;

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

    @Builder
    private OrderDetail(Long id,
                       Order order,
                       String productCode,
                       String productDetailCode,
                       String productName,
                       String size,
                       String color,
                       int quantity,
                       int price,
                       String carvingContent,
                       String categoryName,
                       Boolean carving) {
        this.id = id;
        this.order = order;
        this.productCode = productCode;
        this.productDetailCode = productDetailCode;
        this.productName = productName;
        this.size = size;
        this.color = color;
        this.quantity = quantity;
        this.price = price;
        this.carvingContent = carvingContent;
        this.categoryName = categoryName;
        this.carving = carving;
    }
}


