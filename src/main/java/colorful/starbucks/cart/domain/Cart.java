package colorful.starbucks.cart.domain;

import colorful.starbucks.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    @Column(nullable = false)
    private String memberUuid;

    @Column(nullable = false)
    private boolean checked;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private String productDetailCode;

    @Column(nullable = true)
    private String carvingContent;

    @Column(nullable = false)
    private int price;

    @Builder
    private Cart(Long id,
                 String memberUuid,
                 boolean checked,
                 int quantity,
                 String productDetailCode,
                 String carvingContent,
                 int price) {
        this.id = id;
        this.memberUuid = memberUuid;
        this.checked = checked;
        this.quantity = quantity;
        this.productDetailCode = productDetailCode;
        this.carvingContent = carvingContent;
        this.price = price;
    }
}
